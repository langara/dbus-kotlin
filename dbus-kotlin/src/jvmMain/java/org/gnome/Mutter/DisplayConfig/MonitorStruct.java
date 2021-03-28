package org.gnome.Mutter.DisplayConfig;
import java.util.Map;
import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.Variant;

public final class MonitorStruct extends Struct
{
   @Position(0)
   public final CharSequence connector;
   @Position(1)
   public final CharSequence monitorModeId;
   @Position(2)
   public final Map<CharSequence,Variant> monitorProperties;
  public MonitorStruct(CharSequence connector, CharSequence monitorModeId, Map<CharSequence,Variant> monitorProperties)
  {
   this.connector = connector;
   this.monitorModeId = monitorModeId;
   this.monitorProperties = monitorProperties;
  }
}
