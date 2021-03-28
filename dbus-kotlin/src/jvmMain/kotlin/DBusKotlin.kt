package pl.mareklangiewicz.dbuskotlin

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.freedesktop.IScreenSaver
import org.freedesktop.dbus.Struct
import org.freedesktop.dbus.Tuple
import org.freedesktop.dbus.connections.AbstractConnection
import org.freedesktop.dbus.connections.impl.DBusConnection
import org.freedesktop.dbus.connections.impl.DBusConnection.DBusBusType.SESSION
import org.freedesktop.dbus.connections.impl.DBusConnection.DBusBusType.SYSTEM
import org.freedesktop.dbus.exceptions.DBusExecutionException
import org.freedesktop.dbus.interfaces.CallbackHandler
import org.freedesktop.dbus.interfaces.DBusInterface
import org.freedesktop.dbus.interfaces.Introspectable
import org.freedesktop.dbus.types.UInt16
import org.freedesktop.dbus.types.UInt32
import org.freedesktop.dbus.types.UInt64
import org.freedesktop.dbus.types.Variant
import org.gnome.Mutter.DisplayConfig.MonitorConfStruct
import org.gnome.Mutter.DisplayConfig.MonitorStruct
import org.gnome.Mutter.IDisplayConfig
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties

fun dbusConnect(system: Boolean = false): DBusConnection = DBusConnection.getConnection(if (system) SYSTEM else SESSION)

fun dbusDefaultObjPath(busName: String) = "/" + busName.replace('.', '/')

inline fun <reified T: DBusInterface> DBusConnection.getRO(
    busName: String,
    objPath: String = dbusDefaultObjPath(busName)
): T = getRemoteObject(busName, objPath, T::class.java)

fun dbusMutterIntro() = dbusConnect().getRO<Introspectable>("org.gnome.Mutter.DisplayConfig").Introspect()

fun dbusScreenSaver() = dbusConnect().getRO<IScreenSaver>("org.freedesktop.ScreenSaver")

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
    val config = dbusConnect().getRO<IDisplayConfig>("org.gnome.Mutter.DisplayConfig")
    return config.GetResources()
}

fun dbusMutterGetResourcesByHand(): Any { // FIXME: does not work - rethink dynamic calls (without code generation)
    val connect = dbusConnect()
    val interf = connect.getRO<DBusInterface>("org.gnome.Mutter.DisplayConfig")
    return runBlocking { connect.dbusCall<Any>(interf, "GetResources") }
}

fun dbusApplyMyBothMonitorsConfig() {
    val conn = DBusConnection.getConnection(SESSION)
    val config = conn.getRemoteObject(
        "org.gnome.Mutter.DisplayConfig",
        "/org/gnome/Mutter/DisplayConfig",
        IDisplayConfig::class.java
    )
    val configurationSerial = config.GetResources().a
    config.ApplyMonitorsConfig(
        configurationSerial, // configuration serial
        UInt32(2), // configuration method (2:persistent)
        listOf(
            // logical monitors
            MonitorConfStruct(
                0, 0, 1.0, UInt32(0), true, listOf(
                    MonitorStruct(
                        "DP-2", "2560x1440@59.950550079345703", mapOf(
                            "underscanning" to Variant(false)
                        )
                    )
                )
            ),
            MonitorConfStruct(
                267, 1440, 1.0, UInt32(0), false, listOf(
                    MonitorStruct(
                        "eDP-1", "1920x1080@59.999324798583984", mapOf(
                            "underscanning" to Variant(false)
                        )
                    )
                )
            ),
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
