package org.gnome.Mutter.DisplayConfig;
import java.util.List;
import java.util.Map;
import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.Variant;

public final class Struct6 extends Struct
{
   @Position(0)
   public final MonitorStruct a;
   @Position(1)
   public final List<Struct10> b;
   @Position(2)
   public final Map<CharSequence,Variant> c;
  public Struct6(MonitorStruct a, List<Struct10> b, Map<CharSequence,Variant> c)
  {
   this.a = a;
   this.b = b;
   this.c = c;
  }
}
