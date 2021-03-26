import org.freedesktop.dbus.utils.generator.InterfaceCodeGenerator
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import pl.mareklangiewicz.dbuskotlin.dbusMutterIntro
import kotlin.test.Ignore


// TODO later: move all generated interfaces to separate gradle modules (libs) like dbus-kotlin-gnome, etc..

internal class DBusKotlinGenerator {

    @Test
    fun dbusGenerateSomeMutterStuff() {
        InterfaceCodeGenerator.main(arrayOf(
            "org.gnome.Mutter.DisplayConfig",
            "/org/gnome/Mutter/DisplayConfig",
            "--session",
//        "--enable-dtd-validation",
            "--outputDir",
            "../dbus-kotlin/src/jvmMain/java",
        ))

    }
}
