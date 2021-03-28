import org.junit.jupiter.api.Test

import pl.mareklangiewicz.dbuskotlin.dbusApplyMyBothMonitorsConfig
import pl.mareklangiewicz.dbuskotlin.dbusMutterGetResources
import pl.mareklangiewicz.dbuskotlin.dbusMutterIntro
import pl.mareklangiewicz.dbuskotlin.toKotlinStruct
import java.io.File

internal class DBusKotlinTest {

    @Test
    fun dbusMutterIntroTest() {
        val xml = dbusMutterIntro()
        println(xml)
    }

    @Test
    fun dbusMutterGetResourcesTest() {
        val res = dbusMutterGetResources()
        val kotlinStruct = res.toKotlinStruct()
        println(kotlinStruct)
        val path = "../dbus-kotlin/src/jvmMain/kotlin"
        File(path).mkdirs()
        File("$path/Experiment.kt").writeText("""
            import org.freedesktop.dbus.*
            import org.freedesktop.dbus.types.*
            import org.gnome.Mutter.DisplayConfig.*
            
            val kotlinStruct = $kotlinStruct""".trimIndent())
    }

    @Test
    fun dbusMutterApplyBothMonitorsConfigTest() {
        dbusApplyMyBothMonitorsConfig()
    }
}
