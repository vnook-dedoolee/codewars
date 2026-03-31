// https://www.codewars.com/kata/56347fcfd086de8f11000014/train/kotlin

package kyu_5.eulers_method_for_a_first_order_ode.kotlin

import kotlin.math.abs
import kotlin.math.exp

object EulerOde {
    fun exEuler(n: Int): Double {
        val h = 1.0 / n
        var x = 0.0
        var y = 1.0
        var sumError = 0.0

        for (k in 0..n) {
            val z = 1 + 0.5 * exp(-4 * x) - 0.5 * Math.exp(-2 * x)

            if (z != 0.0) {
                sumError += abs(y - z) / z
            }

            if (k < n) {
                y += (2 - exp(-4 * x) - 2 * y) * h
                x += h
            }
        }

        return (sumError / (n + 1) * 1_000_000).toLong() / 1_000_000.0
    }
}
