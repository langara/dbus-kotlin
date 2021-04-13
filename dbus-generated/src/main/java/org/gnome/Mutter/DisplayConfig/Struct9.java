package org.gnome.Mutter.DisplayConfig;
import java.util.Map;
import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.Variant;
import org.gnome.Mutter.DisplayConfig.*;
public final class Struct9 extends Struct
{
   @Position(0)
   public final CharSequence a;
   @Position(1)
   public final CharSequence b;
   @Position(2)
   public final Map<CharSequence,Variant> c;
  public Struct9(CharSequence a, CharSequence b, Map<CharSequence,Variant> c)
  {
   this.a = a;
   this.b = b;
   this.c = c;
  }
}
