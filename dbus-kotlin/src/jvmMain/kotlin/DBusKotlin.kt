package pl.mareklangiewicz.dbuskotlin

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.freedesktop.dbus.connections.AbstractConnection
import org.freedesktop.dbus.connections.impl.DBusConnection
import org.freedesktop.dbus.exceptions.DBusExecutionException
import org.freedesktop.dbus.interfaces.CallbackHandler
import org.freedesktop.dbus.interfaces.DBusInterface
import org.freedesktop.dbus.interfaces.Introspectable
import org.freedesktop.dbus.types.UInt32
import org.freedesktop.dbus.types.Variant
import org.gnome.mutter.ApplyMonitorsConfigStruct
import org.gnome.mutter.DisplayConfig
import org.gnome.mutter.GetResourcesTuple
import org.gnome.mutter.MyLocalMonitorStruct
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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

fun dbusMutterGetResources(): String {
    val conn = DBusConnection.getConnection(DBusConnection.DBusBusType.SESSION)
    val interf = conn.getRemoteObject("org.gnome.Mutter.DisplayConfig", "/org/gnome/Mutter/DisplayConfig", DisplayConfig::class.java)
    val resources = runBlocking { conn.dbusCall<Any>(interf, "GetResources") }
    return resources.toString()
}

fun dbusApplyMyBothMonitorsConfig() {
    val conn = DBusConnection.getConnection(DBusConnection.DBusBusType.SESSION)
    val config = conn.getRemoteObject("org.gnome.Mutter.DisplayConfig", "/org/gnome/Mutter/DisplayConfig", DisplayConfig::class.java)
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
            ApplyMonitorsConfigStruct(346, 1440, 1.0, UInt32(0), false, listOf(
                MyLocalMonitorStruct("eDP-1", "1920x1080@59.999324798583984", mapOf(
                    "underscanning" to Variant(false)
                )))
            ),
            ApplyMonitorsConfigStruct(0, 0, 1.0, UInt32(0), true, listOf(
                MyLocalMonitorStruct("DP-2", "2560x1440@59.950550079345703", mapOf(
                    "underscanning" to Variant(false)
                )))
            )
        ),
        mapOf()
    )
}
