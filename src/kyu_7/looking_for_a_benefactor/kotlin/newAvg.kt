// https://www.codewars.com/kata/569b5cec755dd3534d00000f/train/kotlin

package kyu_7.looking_for_a_benefactor.kotlin

import kotlin.math.ceil

fun newAvg(arr: DoubleArray, navg: Double): Long {
    val sum = arr.sum()
    val n = arr.size
    val result = navg * (n + 1) - sum
    require(result > 0) { throw IllegalArgumentException() }
    return ceil(result).toLong()
}