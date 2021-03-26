import org.freedesktop.dbus.utils.generator.InterfaceCodeGenerator
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import pl.mareklangiewicz.dbuskotlin.dbusMutterIntro
import kotlin.test.Ignore

internal class DBusKotlinGenerator {

    @Ignore
    @Test
    fun dbusGenerateSomeMutterStuff() {
        InterfaceCodeGenerator.main(arrayOf(
            "org.gnome.Mutter.DisplayConfig",
            "/org/gnome/Mutter/DisplayConfig",
            "--session",
//        "--enable-dtd-validation",
            "--outputDir",
            "../src/jvmMain/java",
        ))

    }
}
