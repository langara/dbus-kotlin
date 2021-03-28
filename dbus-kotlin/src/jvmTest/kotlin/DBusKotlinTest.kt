import kotlin.test.Ignore
import org.junit.jupiter.api.Test

import pl.mareklangiewicz.dbuskotlin.dbusApplyMyBothMonitorsConfig
import pl.mareklangiewicz.dbuskotlin.dbusMutterGetResources
import pl.mareklangiewicz.dbuskotlin.dbusMutterGetResourcesByHand
import pl.mareklangiewicz.dbuskotlin.dbusMutterIntro
import pl.mareklangiewicz.dbuskotlin.dbusScreenSaver
import pl.mareklangiewicz.dbuskotlin.toKotlinStruct
import java.io.File

internal class DBusKotlinTest {

    @Ignore
    @Test
    fun dbusMutterIntroTest() {
        val xml = dbusMutterIntro()
        println(xml)
    }

    @Ignore
    @Test
    fun dbusScreenSaverLockTest() = dbusScreenSaver().Lock()
    // Error: this method is not implemented..

    @Ignore
    @Test
    fun dbusScreenSaverSetActiveTest() { dbusScreenSaver().SetActive(true) }
    // Error: this method is not implemented..

    @Ignore
    @Test
    fun dbusScreenSaverGetSessionIdleTime() { println("session idle time: ${dbusScreenSaver().GetSessionIdleTime()}") }
    // Error: this method is not implemented..

    @Ignore
    @Test
    fun dbusMutterGetResourcesTest() {
        val res = dbusMutterGetResources()
//        val res = dbusMutterGetResourcesByHand()
        val kotlinStruct = res.toKotlinStruct()
        println(kotlinStruct)
        val path = "src/jvmMain/kotlin"
        File(path).mkdirs()
        File("$path/Experiment.kt").writeText("""
            import org.freedesktop.dbus.*
            import org.freedesktop.dbus.types.*
            import org.gnome.Mutter.DisplayConfig.*
            
            private val kotlinStruct = $kotlinStruct""".trimIndent())
    }

    @Ignore
    @Test
    fun dbusMutterApplyBothMonitorsConfigTest() {
        dbusApplyMyBothMonitorsConfig()
    }
}
