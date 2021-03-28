package org.gnome.Mutter.DisplayConfig;
import java.util.List;
import java.util.Map;
import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;
import org.gnome.Mutter.DisplayConfig.*;
public final class Struct1 extends Struct
{
   @Position(0)
   public final UInt32 a;
   @Position(1)
   public final long b;
   @Position(2)
   public final int c;
   @Position(3)
   public final int d;
   @Position(4)
   public final int e;
   @Position(5)
   public final int f;
   @Position(6)
   public final int g;
   @Position(7)
   public final UInt32 h;
   @Position(8)
   public final List<UInt32> i;
   @Position(9)
   public final Map<CharSequence,Variant> j;
  public Struct1(UInt32 a, long b, int c, int d, int e, int f, int g, UInt32 h, List<UInt32> i, Map<CharSequence,Variant> j)
  {
   this.a = a;
   this.b = b;
   this.c = c;
   this.d = d;
   this.e = e;
   this.f = f;
   this.g = g;
   this.h = h;
   this.i = i;
   this.j = j;
  }
}
