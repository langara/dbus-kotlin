package org.gnome.Mutter.DisplayConfig;
import java.util.List;
import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.UInt32;

public final class MonitorConfStruct extends Struct
{
   @Position(0)
   public final int layoutXPos;
   @Position(1)
   public final int layoutYPos;
   @Position(2)
   public final double scale;
   @Position(3)
   public final UInt32 transform;
   @Position(4)
   public final boolean primary;
   @Position(5)
   public final List<MonitorStruct> monitors;
  public MonitorConfStruct(int layoutXPos, int layoutYPos, double scale, UInt32 transform, boolean primary, List<MonitorStruct> monitors)
  {
   this.layoutXPos = layoutXPos;
   this.layoutYPos = layoutYPos;
   this.scale = scale;
   this.transform = transform;
   this.primary = primary;
   this.monitors = monitors;
  }
}
