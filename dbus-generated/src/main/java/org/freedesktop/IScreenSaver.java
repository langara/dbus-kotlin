package org.freedesktop;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.UInt32;

@DBusInterfaceName(value = "org.freedesktop.ScreenSaver")
public interface IScreenSaver extends DBusInterface
{
   public static class ActiveChanged extends DBusSignal
   {
      public final boolean arg_0;
      public ActiveChanged(String path, boolean arg_0) throws DBusException
      {
         super(path, arg_0);
         this.arg_0 = arg_0;
      }
   }

  public void Lock();
  public void SimulateUserActivity();
  public boolean GetActive();
  public UInt32 GetActiveTime();
  public UInt32 GetSessionIdleTime();
  public boolean SetActive(boolean e);
  public UInt32 Inhibit(CharSequence application_name, CharSequence reason_for_inhibit);
  public void UnInhibit(UInt32 cookie);
  public UInt32 Throttle(CharSequence application_name, CharSequence reason_for_inhibit);
  public void UnThrottle(UInt32 cookie);

}
