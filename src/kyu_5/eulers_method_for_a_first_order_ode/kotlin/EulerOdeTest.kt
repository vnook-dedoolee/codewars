package kyu_5.eulers_method_for_a_first_order_ode.kotlin

import java.text.DecimalFormat
import org.junit.Test
import kotlin.test.assertEquals

class EulerOdeTest {
    private fun assertFuzzy(exp:Double, act:Double) {
        val inrange = Math.abs(act - exp) <= 1e-6
        if (!inrange)
        {
            val df = DecimalFormat("\"#.000000\"")
            println("At 1e-6: Expected must be " + df.format(exp) + ", but got " + df.format(act))
        }
        assertEquals(true, inrange)
    }
    @Test
    fun test1() {
        assertFuzzy(0.5, EulerOde.exEuler(1))
        assertFuzzy(0.026314, EulerOde.exEuler(10))
        assertFuzzy(0.015193, EulerOde.exEuler(17))

    }
}