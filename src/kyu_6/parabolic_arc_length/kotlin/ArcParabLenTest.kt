package kyu_6.parabolic_arc_length.kotlin

import org.junit.Assert.*
import java.text.DecimalFormat
import org.junit.Test

class ArcParabLenTest {
    private fun assertFuzzyEquals(act:Double, exp:Double) {
        val inrange = Math.abs((act - exp) / exp) <= 1e-6
        if (inrange == false)
        {
            val df = DecimalFormat("#.000000")
            val sact = Math.floor(act * 1e6) / 1e6
            val sexp = Math.floor(exp * 1e6) / 1e6
            println("At 1e-06: Expected must be near " + df.format(sexp) + ", but got " + df.format(sact))
        }
        assertEquals(true, inrange)
    }
    @Test
    fun test1() {
        println("Fixed Tests: lenCurve")
        assertFuzzyEquals(lenCurve(1), 1.414213562)
        assertFuzzyEquals(lenCurve(10), 1.478197397)

    }

}
