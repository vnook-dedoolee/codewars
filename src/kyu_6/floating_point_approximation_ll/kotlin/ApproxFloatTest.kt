package kyu_6.floating_point_approximation_ll.kotlin

import kotlin.test.assertEquals
import org.junit.Test

class ApproxFloatTest {
    fun testing(actual:String, expected:String) {
        assertEquals(expected, actual)
    }
    @Test
    fun test1() {
        println("{ x-> x }")
        testing(interp({ x -> x }, 0.0, 9.0, 4).toString(),
            "[0.0, 2.25, 4.5, 6.75]")

    }
    @Test
    fun test2() {
        println("{ x-> sin(x) }")
        testing(interp({ x -> Math.sin(x.toDouble()) }, 0.0, 18.0, 12).toString(),
            "[0.0, 0.99, 0.14, -0.98, -0.28, 0.93, 0.41, -0.88, -0.54, 0.8, 0.65, -0.72]")

    }
    @Test
    fun test3() {
        println("{ x-> cos(x) }")
        testing(interp({ x -> Math.cos(x.toDouble()) }, 0.0, 21.0, 7).toString(),
            "[1.0, -0.99, 0.96, -0.92, 0.84, -0.76, 0.66]")

    }

}
