package kyu_5.interlaced_spiral_cipher.kotlin

import org.junit.Test
import kotlin.test.assertEquals

class ExampleTests {
    private fun runTest(s1: String, s2: String) {
        assertEquals(s2, InterlacedSpiralCipher.encode(s1))
        assertEquals(s1, InterlacedSpiralCipher.decode(s2))
    }

    @Test
    fun `Example Test 1`() {
        val example1A = "Romani ite domum"
        val example1B = "Rntodomiimuea  m"
        runTest(example1A, example1B)
    }

    @Test
    fun `Example Test 2`() {
        val example2A = "Sic transit gloria mundi"
        val example2B = "Stsgiriuar i ninmd l otac"
        runTest(example2A, example2B)
    }
}