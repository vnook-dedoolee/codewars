// https://www.codewars.com/kata/5b0c0ec907756ffcff00006e/train/kotlin

package kyu_7.floating_point_approximation_III.kotlin

import kotlin.math.sqrt

fun quadratic(a: Double, b: Double, c: Double): Double {
    val discriminant = b * b - 4 * a * c
    val sqrtDiscriminant = sqrt(discriminant)
    return -2 * c / (b + sqrtDiscriminant)
}
