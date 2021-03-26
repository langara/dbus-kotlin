import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import pl.mareklangiewicz.dbuskotlin.dbusMutterIntro
import kotlin.test.Ignore

internal class DBusKotlinTest {

    @Test
    @Ignore
    fun dbusMutterIntroTest() {
        val xml = dbusMutterIntro()
        println(xml)
    }
}
