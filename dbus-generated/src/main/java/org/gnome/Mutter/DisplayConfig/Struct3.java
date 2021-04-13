package org.gnome.Mutter.DisplayConfig;
import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.UInt32;
import org.gnome.Mutter.DisplayConfig.*;
public final class Struct3 extends Struct
{
   @Position(0)
   public final UInt32 a;
   @Position(1)
   public final long b;
   @Position(2)
   public final UInt32 c;
   @Position(3)
   public final UInt32 d;
   @Position(4)
   public final double e;
   @Position(5)
   public final UInt32 f;
  public Struct3(UInt32 a, long b, UInt32 c, UInt32 d, double e, UInt32 f)
  {
   this.a = a;
   this.b = b;
   this.c = c;
   this.d = d;
   this.e = e;
   this.f = f;
  }
}
