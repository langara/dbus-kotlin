package GetCurrentStateStructStruct;

import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;

/**
 * Auto-generated class.
 */
public class GetCurrentStateStructStructStruct extends Struct {
    @Position(0)
    private final Struct member0;

    public GetCurrentStateStructStructStruct(Struct member0) {
        this.member0 = member0;
    }


    public Struct getMember0() {
        return member0;
    }


}