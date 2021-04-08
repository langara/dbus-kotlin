package org.gnome.Mutter;

import java.util.List;
import java.util.Map;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.UInt16;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;
import org.gnome.Mutter.DisplayConfig.*;
import org.gnome.Mutter.DisplayConfig.Quad;
import org.gnome.Mutter.DisplayConfig.Sextuple;
import org.gnome.Mutter.DisplayConfig.Triplet;

@DBusInterfaceName(value = "org.gnome.Mutter.DisplayConfig")
public interface IDisplayConfig extends DBusInterface
{
   public static class MonitorsChanged extends DBusSignal
   {
      public MonitorsChanged(String path) throws DBusException
      {
         super(path);
      }
   }

  public Sextuple<UInt32, List<Struct1>, List<Struct2>, List<Struct3>, Integer, Integer> GetResources();
  public void ApplyConfiguration(UInt32 serial, boolean persistent, List<Struct4> crtcs, List<Struct5> outputs);
  public int ChangeBacklight(UInt32 serial, UInt32 output, int value);
  public Triplet<List<UInt16>, List<UInt16>, List<UInt16>> GetCrtcGamma(UInt32 serial, UInt32 crtc);
  public void SetCrtcGamma(UInt32 serial, UInt32 crtc, List<UInt16> red, List<UInt16> green, List<UInt16> blue);
  public Quad<UInt32, List<Struct6>, List<Struct7>, Map<CharSequence,Variant>> GetCurrentState();
  public void ApplyMonitorsConfig(UInt32 serial, UInt32 method, List<MonitorConfStruct> logical_monitors, Map<CharSequence,Variant> properties);

}
