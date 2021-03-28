package org.gnome.Mutter.DisplayConfig;
import java.util.List;
import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.UInt32;
import org.gnome.Mutter.DisplayConfig.*;
public final class Struct8 extends Struct
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
   public final List<Struct9> f;
  public Struct8(int a, int b, double c, UInt32 d, boolean e, List<Struct9> f)
  {
   this.a = a;
   this.b = b;
   this.c = c;
   this.d = d;
   this.e = e;
   this.f = f;
  }
}
