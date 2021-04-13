package org.freedesktop;

import java.util.List;
import java.util.Map;
import org.freedesktop.NetworkManager.Pair;
import org.freedesktop.NetworkManager.Triplet;
import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;

@DBusInterfaceName(value = "org.freedesktop.NetworkManager")
public interface INetworkManager extends DBusInterface
{
   public static class PropertiesChanged extends DBusSignal
   {
      public final Map<CharSequence,Variant> properties;
      public PropertiesChanged(String path, Map<CharSequence,Variant> properties) throws DBusException
      {
         super(path, properties);
         this.properties = properties;
      }
   }
   public static class CheckPermissions extends DBusSignal
   {
      public CheckPermissions(String path) throws DBusException
      {
         super(path);
      }
   }
   public static class StateChanged extends DBusSignal
   {
      public final UInt32 state;
      public StateChanged(String path, UInt32 state) throws DBusException
      {
         super(path, state);
         this.state = state;
      }
   }
   public static class DeviceAdded extends DBusSignal
   {
      public final DBusPath device_path;
      public DeviceAdded(String path, DBusPath device_path) throws DBusException
      {
         super(path, device_path);
         this.device_path = device_path;
      }
   }
   public static class DeviceRemoved extends DBusSignal
   {
      public final DBusPath device_path;
      public DeviceRemoved(String path, DBusPath device_path) throws DBusException
      {
         super(path, device_path);
         this.device_path = device_path;
      }
   }

  public void Reload(UInt32 flags);
  public List<DBusPath> GetDevices();
  public List<DBusPath> GetAllDevices();
  public DBusPath GetDeviceByIpIface(CharSequence iface);
  public DBusPath ActivateConnection(DBusPath connection, DBusPath device, DBusPath specific_object);
  public Pair<DBusPath, DBusPath> AddAndActivateConnection(Map<CharSequence,Map<CharSequence,Variant>> connection, DBusPath device, DBusPath specific_object);
  public Triplet<DBusPath, DBusPath, Map<CharSequence,Variant>> AddAndActivateConnection2(Map<CharSequence,Map<CharSequence,Variant>> connection, DBusPath device, DBusPath specific_object, Map<CharSequence,Variant> options);
  public void DeactivateConnection(DBusPath active_connection);
  public void Sleep(boolean sleep);
  public void Enable(boolean enable);
  public Map<CharSequence,CharSequence> GetPermissions();
  public void SetLogging(CharSequence level, CharSequence domains);
  public Pair<CharSequence, CharSequence> GetLogging();
  public UInt32 CheckConnectivity();
  public UInt32 state();
  public DBusPath CheckpointCreate(List<DBusPath> devices, UInt32 rollback_timeout, UInt32 flags);
  public void CheckpointDestroy(DBusPath checkpoint);
  public Map<CharSequence,UInt32> CheckpointRollback(DBusPath checkpoint);
  public void CheckpointAdjustRollbackTimeout(DBusPath checkpoint, UInt32 add_timeout);

}
