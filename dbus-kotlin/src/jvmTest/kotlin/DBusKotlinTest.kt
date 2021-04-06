import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import pl.mareklangiewicz.dbuskotlin.*

import java.io.File

@Tag("integration")
internal class DBusKotlinTest {

    @Test
    fun dbusMutterIntroTest() {
        val xml = dbusMutterIntro()
        println(xml)
    }

    @Test
    fun dbusNetworkManagerPropertiesTest() {
        val props = dbusNetworkManagerProperties().GetAll("org.freedesktop.NetworkManager")
        println(props.toString())
    }

    @Test
    fun dbusNetworkManagerWifiEnableTest() = dbusNetworkManagerWifi(true)

    @Test
    fun dbusScreenSaverLockTest() = dbusScreenSaver().Lock()
    // Error: this method is not implemented..

    @Test
    fun dbusScreenSaverSetActiveTest() { dbusScreenSaver().SetActive(true) }
    // Error: this method is not implemented..

    @Test
    fun dbusScreenSaverGetSessionIdleTime() { println("session idle time: ${dbusScreenSaver().GetSessionIdleTime()}") }
    // Error: this method is not implemented..

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

    @Test
    fun dbusMutterApplyBothMonitorsConfigTest() {
        dbusApplyMyBothMonitorsConfig()
    }

    @Test
    fun dbusDaemonTest() {
        dbusDaemonMain(
            "--tcp",
            "--print-address",
        )
    }

    @Test
    fun getInet4AddressesTest() {
        println(getInet4Addresses())
    }

    @Test
    fun dbusConnectAddrTest() {
        dbusConnectAddr("tcp:host=192.168.1.35,port=12468", timeout = 300)
    }
}
