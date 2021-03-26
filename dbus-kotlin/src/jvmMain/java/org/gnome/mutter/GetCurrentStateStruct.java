package org.gnome.mutter;

import java.util.List;
import java.util.Map;
import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;

/**
 * Auto-generated class.
 */
public class GetCurrentStateStruct extends Struct {
    @Position(0)
    private final int member0;
    @Position(1)
    private final int member1;
    @Position(2)
    private final double member2;
    @Position(3)
    private final UInt32 member3;
    @Position(4)
    private final boolean member4;
    @Position(5)
    private final List member5;
    @Position(6)
    private final Map<String, Variant<?>> member6;

    public GetCurrentStateStruct(int member0, int member1, double member2, UInt32 member3, boolean member4, List member5, Map<String, Variant<?>> member6) {
        this.member0 = member0;
        this.member1 = member1;
        this.member2 = member2;
        this.member3 = member3;
        this.member4 = member4;
        this.member5 = member5;
        this.member6 = member6;
    }


    public int getMember0() {
        return member0;
    }

    public int getMember1() {
        return member1;
    }

    public double getMember2() {
        return member2;
    }

    public UInt32 getMember3() {
        return member3;
    }

    public boolean getMember4() {
        return member4;
    }

    public List getMember5() {
        return member5;
    }

    public Map<String, Variant<?>> getMember6() {
        return member6;
    }


}