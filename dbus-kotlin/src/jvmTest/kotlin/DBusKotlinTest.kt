import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import pl.mareklangiewicz.dbuskotlin.dbusApplyMyBothMonitorsConfig
import pl.mareklangiewicz.dbuskotlin.dbusMutterGetResources
import pl.mareklangiewicz.dbuskotlin.dbusMutterIntro
import kotlin.test.Ignore

internal class DBusKotlinTest {

    @Test
    fun dbusMutterIntroTest() {
        val xml = dbusMutterIntro()
        println(xml)
    }

    @Test
    fun dbusMutterGetResourcesTest() {
        dbusMutterGetResources()
    }

    @Test
    fun dbusMutterApplyBothMonitorsConfigTest() {
        dbusApplyMyBothMonitorsConfig()
    }
}
