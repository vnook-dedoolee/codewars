package kyu_7.geometric_progression_sequence.kotlin

import org.junit.Test
import kotlin.test.assertEquals

class GeometricSequenceTest {

    private fun test(actual: String, expected: String) = assertEquals(expected, actual)

    @Test
    fun exampleTests() {
        test(geometricSequenceElements(2, 3, 5), "2, 6, 18, 54, 162")
        test(geometricSequenceElements(2, 2, 10), "2, 4, 8, 16, 32, 64, 128, 256, 512, 1024")
        test(geometricSequenceElements(1, -2, 10), "1, -2, 4, -8, 16, -32, 64, -128, 256, -512")
    }
}