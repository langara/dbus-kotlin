package org.gnome.Mutter.DisplayConfig;
import java.util.Map;
import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;
import org.gnome.Mutter.DisplayConfig.*;
public final class Struct5 extends Struct
{
   @Position(0)
   public final UInt32 a;
   @Position(1)
   public final Map<CharSequence,Variant> b;
  public Struct5(UInt32 a, Map<CharSequence,Variant> b)
  {
   this.a = a;
   this.b = b;
  }
}
