package kyu_6.sha_256_cracker.kotlin

import org.junit.Test
import kotlin.test.assertEquals

class CrackerTest {

    @Test
    fun testExamples() {
        assertEquals("GoOutside", crackSha256("b8c49d81cb795985c007d78379e98613a4dfc824381be472238dbd2f974e37ae", "deGioOstu"))
        assertEquals(null, crackSha256("f58262c8005bb64b8f99ec6083faf050c502d099d9929ae37ffed2fe1bb954fb", "abc"))
    }
}