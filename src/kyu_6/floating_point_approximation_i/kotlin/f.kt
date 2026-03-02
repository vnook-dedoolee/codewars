// https://www.codewars.com/kata/58184387d14fc32f2b0012b2/train/kotlin

package kyu_6.floating_point_approximation_i.kotlin

import kotlin.math.sqrt

fun f(x: Double): Double {
    return x / (sqrt(1.0 + x) + 1.0)
}