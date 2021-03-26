import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import pl.mareklangiewicz.dbuskotlin.dbusMutterIntro

internal class DBusKotlinTest {

    @Test
    fun dbusMutterIntroTest() {
        val xml = dbusMutterIntro()
        println(xml)
    }
}
