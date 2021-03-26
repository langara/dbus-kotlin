package GetCurrentStateStruct;

import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;

/**
 * Auto-generated class.
 */
public class GetCurrentStateStructStruct extends Struct {
    @Position(0)
    private final Struct member0;

    public GetCurrentStateStructStruct(Struct member0) {
        this.member0 = member0;
    }


    public Struct getMember0() {
        return member0;
    }


}