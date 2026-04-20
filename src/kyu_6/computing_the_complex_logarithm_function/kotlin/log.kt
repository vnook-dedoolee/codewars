// https://www.codewars.com/kata/590ba2baf06c49595f0000a0/train/kotlin

package kyu_6.computing_the_complex_logarithm_function.kotlin

import kotlin.math.*

fun log(z: DoubleArray): DoubleArray {
    val x = z[0]
    val y = z[1]

    if (x == 0.0 && y == 0.0) {
        throw ArithmeticException()
    }

    val realPart = 0.5 * ln(x * x + y * y)
    val imagPart = atan2(y, x)

    return doubleArrayOf(realPart, imagPart)
}