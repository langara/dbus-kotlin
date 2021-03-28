import org.freedesktop.dbus.utils.bin.CreateInterface
import org.freedesktop.dbus.utils.generator.InterfaceCodeGenerator
import org.junit.jupiter.api.Test
import pl.mareklangiewicz.dbuskotlin.dbusDefaultObjPath
import java.io.File
import kotlin.test.Ignore
import kotlin.test.fail


// TODO later: move all generated interfaces to separate gradle modules (libs) like dbus-kotlin-gnome, etc..

internal class DBusKotlinGenerator {

    fun dbusCreateInterface(
        busName: String,
        objectPath: String = dbusDefaultObjPath(busName),
        systemBus: Boolean = false
    ) {
        val outputDir = File(objectPath.trimStart('/'))
        outputDir.exists() || outputDir.mkdirs() || fail("Can not create $outputDir")
        CreateInterface.main(arrayOf(if (systemBus) "--system" else "--session", "--create-files", busName, objectPath))
        // TODO_later: implement own code generation in kotlin. Both CreateInterface and InterfaceCodeGenerator are broken
    }

    @Ignore
    @Test
    fun dbusCreateInterfaceMutterDisplayConfig() = dbusCreateInterface("org.gnome.Mutter.DisplayConfig")

    @Ignore
    @Test
    fun dbusCreateInterfaceScreenSaver() = dbusCreateInterface("org.freedesktop.ScreenSaver")

    @Test
    fun dbusCreateInterfaceNetworkManager() = dbusCreateInterface("org.freedesktop.NetworkManager", systemBus = true)

    @Ignore
    @Test
    fun dbusGenerateSomeMutterStuff() { // Broken: InterfaceCodeGenerator does not create correct tupples as return values
        InterfaceCodeGenerator.main(
            arrayOf(
                "org.gnome.Mutter.DisplayConfig",
                "/org/gnome/Mutter/DisplayConfig",
//            "--system",
                "--session",
//        "--enable-dtd-validation",
                "--outputDir",
                "../dbus-kotlin/src/jvmMain/java",
            )
        )
    }
}
