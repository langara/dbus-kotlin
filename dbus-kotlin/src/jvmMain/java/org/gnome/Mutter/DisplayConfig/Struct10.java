package org.gnome.Mutter.DisplayConfig;
import java.util.List;
import java.util.Map;
import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.Variant;
import org.gnome.Mutter.DisplayConfig.*;
public final class Struct10 extends Struct
{
   @Position(0)
   public final CharSequence a;
   @Position(1)
   public final int b;
   @Position(2)
   public final int c;
   @Position(3)
   public final double d;
   @Position(4)
   public final double e;
   @Position(5)
   public final List<Double> f;
   @Position(6)
   public final Map<CharSequence,Variant> g;
  public Struct10(CharSequence a, int b, int c, double d, double e, List<Double> f, Map<CharSequence,Variant> g)
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
