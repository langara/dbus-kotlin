package pl.mareklangiewicz.dbuskotlin

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.freedesktop.dbus.Struct
import org.freedesktop.dbus.Tuple
import org.freedesktop.dbus.connections.AbstractConnection
import org.freedesktop.dbus.connections.impl.DBusConnection
import org.freedesktop.dbus.exceptions.DBusExecutionException
import org.freedesktop.dbus.interfaces.CallbackHandler
import org.freedesktop.dbus.interfaces.DBusInterface
import org.freedesktop.dbus.interfaces.Introspectable
import org.freedesktop.dbus.types.UInt16
import org.freedesktop.dbus.types.UInt32
import org.freedesktop.dbus.types.UInt64
import org.freedesktop.dbus.types.Variant
import org.gnome.Mutter.DisplayConfig.Struct8
import org.gnome.Mutter.DisplayConfig.Struct9
import org.gnome.Mutter.IDisplayConfig
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties

fun dbusMutterIntro(): String {
    val conn = DBusConnection.getConnection(DBusConnection.DBusBusType.SESSION)
    val introspectable = conn.getRemoteObject("org.gnome.Mutter.DisplayConfig", "/org/gnome/Mutter/DisplayConfig", Introspectable::class.java)
    val xml = introspectable.Introspect()
    return xml
}

suspend fun <R> AbstractConnection.dbusCall(obj: DBusInterface, method: String, vararg params: Any): R =
    withContext(IO) {
        suspendCoroutine { cont ->
            val handler = object : CallbackHandler<R> {
                override fun handle(r: R) = cont.resume(r)
                override fun handleError(e: DBusExecutionException) = cont.resumeWithException(e)
            }
            callWithCallback(obj, method, handler, *params)
        }
    }

fun dbusMutterGetResources(): Any {
    val conn = DBusConnection.getConnection(DBusConnection.DBusBusType.SESSION)
    val config = conn.getRemoteObject("org.gnome.Mutter.DisplayConfig", "/org/gnome/Mutter/DisplayConfig", IDisplayConfig::class.java)
    val resources = config.GetResources()
    return resources
}

fun dbusMutterGetResourcesByHand(): String {
    val conn = DBusConnection.getConnection(DBusConnection.DBusBusType.SESSION)
    val interf = conn.getRemoteObject("org.gnome.Mutter.DisplayConfig", "/org/gnome/Mutter/DisplayConfig", IDisplayConfig::class.java)
    val resources = runBlocking { conn.dbusCall<Any>(interf, "GetResources") }
    return resources.toString()
}

fun dbusApplyMyBothMonitorsConfig() {
    val conn = DBusConnection.getConnection(DBusConnection.DBusBusType.SESSION)
    val config = conn.getRemoteObject("org.gnome.Mutter.DisplayConfig", "/org/gnome/Mutter/DisplayConfig", IDisplayConfig::class.java)
    // FIXME: I get this from Bustle app:
    //  (
    //      uint32 17,
    //      uint32 2,
    //      [
    //          (
    //              346, 1440, 1.0, uint32 0, false,
    //              [
    //                  (
    //                      'eDP-1', '1920x1080@59.999324798583984',
    //                      { 'underscanning': <false> }
//                      )
//                  ]
//              ),
//              (
//                  0, 0, 1.0, 0, true,
//                  [
//                      (
//                          'DP-2', '2560x1440@59.950550079345703',
//                          {'underscanning': <false>}
//                      )
//                  ]
//              )
//          ],
//          @a{sv} {}
//      )0
    // TODO: translate it to kotlin below...
    config.ApplyMonitorsConfig(
        UInt32(17), UInt32(2), listOf(
            Struct8(346, 1440, 1.0, UInt32(0), false, listOf(
                Struct9("eDP-1", "1920x1080@59.999324798583984", mapOf(
                    "underscanning" to Variant(false)
                )))
            ),
            Struct8(0, 0, 1.0, UInt32(0), true, listOf(
                Struct9("DP-2", "2560x1440@59.950550079345703", mapOf(
                    "underscanning" to Variant(false)
                )))
            )
        ),
        mapOf()
    )
}


public fun Any?.toKotlinStruct(): String = when {
    this === null -> "null"
    this is String -> "\"$this\""
    this is List<*> -> "listOf(${joinToString { it.toKotlinStruct() }})"

    // FIXME: I assume keys are strings (rethink)
    this is Map<*, *> -> {
        val entriesStr = entries.joinToString { "\"${it.key}\" to ${it.value.toKotlinStruct()}" }
        "mapOf($entriesStr)"
    }
    (this is Tuple) or (this is Struct) -> {
        val propsStr = getPublicPropsVals().joinToString { it.toKotlinStruct() }
        "${this::class.simpleName}($propsStr)"
    }
    (this is UInt16) -> "UInt16(${this.toInt()})"
    (this is UInt32) -> "UInt32(${this.toLong()})"
    (this is UInt64) -> "UInt64(${this.toLong()})"
    this is Variant<*> -> "Variant(${value.toKotlinStruct()})"
    this is ByteArray -> "ByteArrayFixme(\"$this\")"
//    else -> "${this::class.simpleName}($this)" // for debugging
    else -> "$this"
}

@OptIn(ExperimentalStdlibApi::class)
fun <T : Any> T.getPublicPropsVals(): List<Any?> {
    val props = (this::class as KClass<T>).declaredMemberProperties
    return props
        .filter { it.visibility == KVisibility.PUBLIC }
        .map { it(this) }
}
