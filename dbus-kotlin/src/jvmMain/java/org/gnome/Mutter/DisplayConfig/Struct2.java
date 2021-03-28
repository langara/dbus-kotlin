package org.gnome.Mutter.DisplayConfig;
import java.util.List;
import java.util.Map;
import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;
import org.gnome.Mutter.DisplayConfig.*;
public final class Struct2 extends Struct
{
   @Position(0)
   public final UInt32 a;
   @Position(1)
   public final long b;
   @Position(2)
   public final int c;
   @Position(3)
   public final List<UInt32> d;
   @Position(4)
   public final CharSequence e;
   @Position(5)
   public final List<UInt32> f;
   @Position(6)
   public final List<UInt32> g;
   @Position(7)
   public final Map<CharSequence,Variant> h;
  public Struct2(UInt32 a, long b, int c, List<UInt32> d, CharSequence e, List<UInt32> f, List<UInt32> g, Map<CharSequence,Variant> h)
  {
   this.a = a;
   this.b = b;
   this.c = c;
   this.d = d;
   this.e = e;
   this.f = f;
   this.g = g;
   this.h = h;
  }
}
