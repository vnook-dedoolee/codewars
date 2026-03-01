package kyu_6.disease_spread.kotlin

import org.junit.Assert.*
import org.junit.Test
import java.text.DecimalFormat

class epidemyMainTest {
    @Test
    fun test1() {
        println("Fixed Tests: Epidemic")
        var tm = 18
        var n = 432
        var s0 = 1004
        var i0 = 1
        var b = 0.00209
        var a = 0.51
        testing(epidemic(tm, n, s0, i0, b, a), 420)
        tm = 12
        n = 288
        s0 = 1007
        i0 = 2
        b = 0.00206
        a = 0.45
        testing(epidemic(tm, n, s0, i0, b, a), 461)
    }
    companion object {
        private fun testing(actual:Int, expected:Int) {
            val r = Math.abs(actual - expected)
            val inrange = r <= 1
            if (inrange == false)
            {
                val df = DecimalFormat("#.0")
                println("abs(actual - expected) must be <= 1 but was " + df.format(r))
            }
            assertEquals(true, inrange)
        }
    }
}