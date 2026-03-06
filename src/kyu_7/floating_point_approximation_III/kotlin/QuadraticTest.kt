package kyu_7.floating_point_approximation_III.kotlin

import org.junit.Assert.*
import org.junit.Test
import java.util.Random

class QuadraticTest {
    private fun randInt(min:Int, max:Int):Int {
        return min + (Math.random() * ((max - min) + 1)).toInt()
    }
    private fun assertFuzzyEquals(a:Double, b:Double, c:Double) {
        val merr = 1e-12
        println("Testing " + a + ", " + b + ", " + c)
        val x = quadratic(a, b, c)
        val smallest = Math.abs(x) < 1e-1
        if (smallest == false)
        {
            println("This root is not the good one")
        }
        val actual = a * x * x + b * x + c
        val inrange = Math.abs(actual) <= merr
        if (inrange == false)
            println("Expected must be near " + 0.0 + ", got " + actual)
        val correct = smallest && inrange
        assertEquals(true, correct)
    }

    @Test
    fun test1() {
        println("Fixed Tests: quadratic")
        assertFuzzyEquals(7.0, 4.00e+13, 8.0)
        assertFuzzyEquals(9.0, 1.00e+14, 1.0)
        assertFuzzyEquals(3.0, 3.00e+09, 1.0)
        assertFuzzyEquals(7.0, 4.00e+09, 7.0)

    }
}
