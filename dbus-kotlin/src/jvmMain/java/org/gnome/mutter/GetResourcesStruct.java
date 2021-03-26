package org.gnome.mutter;

import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.UInt32;

/**
 * Auto-generated class.
 */
public class GetResourcesStruct extends Struct {
    @Position(0)
    private final UInt32 member0;
    @Position(1)
    private final long member1;
    @Position(2)
    private final UInt32 member2;
    @Position(3)
    private final UInt32 member3;
    @Position(4)
    private final double member4;
    @Position(5)
    private final UInt32 member5;

    public GetResourcesStruct(UInt32 member0, long member1, UInt32 member2, UInt32 member3, double member4, UInt32 member5) {
        this.member0 = member0;
        this.member1 = member1;
        this.member2 = member2;
        this.member3 = member3;
        this.member4 = member4;
        this.member5 = member5;
    }


    public UInt32 getMember0() {
        return member0;
    }

    public long getMember1() {
        return member1;
    }

    public UInt32 getMember2() {
        return member2;
    }

    public UInt32 getMember3() {
        return member3;
    }

    public double getMember4() {
        return member4;
    }

    public UInt32 getMember5() {
        return member5;
    }


}