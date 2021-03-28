package org.gnome.Mutter.DisplayConfig;
import java.util.List;
import java.util.Map;
import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;
import org.gnome.Mutter.DisplayConfig.*;
public final class Struct4 extends Struct
{
   @Position(0)
   public final UInt32 a;
   @Position(1)
   public final int b;
   @Position(2)
   public final int c;
   @Position(3)
   public final int d;
   @Position(4)
   public final UInt32 e;
   @Position(5)
   public final List<UInt32> f;
   @Position(6)
   public final Map<CharSequence,Variant> g;
  public Struct4(UInt32 a, int b, int c, int d, UInt32 e, List<UInt32> f, Map<CharSequence,Variant> g)
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
