package pl.mareklangiewicz.dbuskotlin

import org.freedesktop.dbus.connections.impl.DBusConnection
import org.freedesktop.dbus.interfaces.Introspectable
import org.freedesktop.dbus.types.UInt32

fun dbusMutterIntro(): String {
    val conn = DBusConnection.getConnection(DBusConnection.DBusBusType.SESSION)
    val introspectable = conn.getRemoteObject("org.gnome.Mutter.DisplayConfig", "/org/gnome/Mutter/DisplayConfig", Introspectable::class.java)
    val xml = introspectable.Introspect()
    return xml
}

fun dbusApplyMyBothMonitorsConfig() {
    val conn = DBusConnection.getConnection(DBusConnection.DBusBusType.SESSION)
//    val config = conn.getRemoteObject("org.gnome.Mutter.DisplayConfig", "/org/gnome/Mutter/DisplayConfig", DisplayConfig::class.java)
//    // FIXME: I get this from Bustle app: (uint32 17, uint32 2, [(346, 1440, 1.0, uint32 0, false, [('eDP-1', '1920x1080@59.999324798583984', {'underscanning': <false>})]), (0, 0, 1.0, 0, true, [('DP-2', '2560x1440@59.950550079345703', {'underscanning': <false>})])], @a{sv} {})0
//    // TODO: translate it to kotlin below...
//    config.ApplyMonitorsConfig(
//        UInt32(17), UInt32(2), listOf(
//
//    ),
//        mapOf(
//
//        ))
}
