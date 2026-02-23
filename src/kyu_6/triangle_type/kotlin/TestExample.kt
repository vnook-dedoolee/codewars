package kyu_6.triangle_type.kotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestExample {
    @Test
    fun testExample() {
        doTest(Triple(7.0, 3.0, 2.0), 0)  // Not triangle
        doTest(Triple(2.0, 4.0, 6.0), 0)  // Not triangle
        doTest(Triple(8.0, 5.0, 7.0), 1)  // Acute
        doTest(Triple(3.0, 4.0, 5.0), 2)  // Right
        doTest(Triple(7.0, 12.0, 8.0), 3) // Obtuse
    }
    private fun doTest(sides: Triple<Double,Double,Double>, expected: Int) {
        val (a, b, c) = sides
        val types = arrayOf("INVALID", "ACUTE", "RIGHT", "OBTUSE")
        val msg = "Sides = (${a}, ${b}, ${c}), type = ${types[expected]}"
        assertEquals(expected, triangleType(a, b, c), msg)
    }
}