package org.gnome.mutter;

import java.util.List;
import java.util.Map;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.annotations.DBusProperty;
import org.freedesktop.dbus.annotations.DBusProperty.Access;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;
import org.gnome.mutter.ApplyConfigurationStruct;
import org.gnome.mutter.ApplyMonitorsConfigStruct;
import org.gnome.mutter.GetCurrentStateStruct;
import org.gnome.mutter.GetResourcesStruct;

/**
 * Auto-generated class.
 */
@DBusInterfaceName("org.gnome.Mutter.DisplayConfig")
@DBusProperty(name = "PowerSaveMode", type = Integer.class, access = Access.READ_WRITE)
@DBusProperty(name = "PanelOrientationManaged", type = Boolean.class, access = Access.READ)
public interface DisplayConfig extends DBusInterface {


    public UInt32 GetResources();
    public void ApplyConfiguration(UInt32 serial, boolean persistent, List<ApplyConfigurationStruct> crtcs, List<ApplyConfigurationStruct> outputs);
    public int ChangeBacklight(UInt32 serial, UInt32 output, int value);
    public List<org.freedesktop.dbus.types.UInt16> GetCrtcGamma(UInt32 serial, UInt32 crtc);
    public void SetCrtcGamma(UInt32 serial, UInt32 crtc, List<org.freedesktop.dbus.types.UInt16> red, List<org.freedesktop.dbus.types.UInt16> green, List<org.freedesktop.dbus.types.UInt16> blue);
    public UInt32 GetCurrentState();
    public void ApplyMonitorsConfig(UInt32 serial, UInt32 method, List<ApplyMonitorsConfigStruct> logicalMonitors, Map<String, Variant<?>> properties);

}