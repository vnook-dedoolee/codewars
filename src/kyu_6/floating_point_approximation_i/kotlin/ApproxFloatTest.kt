package kyu_6.floating_point_approximation_i.kotlin

import kotlin.test.assertEquals
import org.junit.Test
import java.util.Random
import java.text.DecimalFormat
import org.junit.Assert.*

class ApproxFloatTest {

    private fun assertFuzzyEquals(act:Double, exp:Double) {
        var inrange:Boolean
        val merr = 1e-12
        if (exp == 0.0)
            inrange = Math.abs(act) <= merr
        else
        {
            val e = Math.abs((act - exp) / exp)
            inrange = e <= 1e-12
        }
        if (inrange == false)
        {
            val df = DecimalFormat("#.0000000000000000")
            println("Expected must be near " + exp + ", but got " + act)
        }
        assertEquals(true, inrange)
    }
    @Test
    fun test1() {
        println("Fixed Tests: f")
        assertFuzzyEquals(f(2.6e-08), 1.29999999155e-08)
        assertFuzzyEquals(f(1.4e-09), 6.999999997549999e-10)

    }

}