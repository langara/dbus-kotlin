package org.gnome.Mutter.DisplayConfig;
import java.util.List;
import java.util.Map;
import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;

public final class Struct7 extends Struct
{
   @Position(0)
   public final int a;
   @Position(1)
   public final int b;
   @Position(2)
   public final double c;
   @Position(3)
   public final UInt32 d;
   @Position(4)
   public final boolean e;
   @Position(5)
   public final List<MonitorStruct> f;
   @Position(6)
   public final Map<CharSequence,Variant> g;
  public Struct7(int a, int b, double c, UInt32 d, boolean e, List<MonitorStruct> f, Map<CharSequence,Variant> g)
  {
   this.a = a;
   this.b = b;
   this.c = c;
   this.d = d;
   this.e = e;
   this.f = f;
   this.g = g;
  }
}
