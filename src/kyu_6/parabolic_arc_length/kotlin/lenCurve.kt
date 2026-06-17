// https://www.codewars.com/kata/562e274ceca15ca6e70000d3/train/kotlin

package kyu_6.parabolic_arc_length.kotlin

import kotlin.math.sqrt

fun lenCurve(n: Int): Double {
    val h = 1.0 / n
    var length = 0.0

    for (i in 0 until n) {
        val x1 = i * h
        val y1 = x1 * x1
        val x2 = (i + 1) * h
        val y2 = x2 * x2

        val dx = x2 - x1
        val dy = y2 - y1

        length += sqrt(dx * dx + dy * dy)
    }

    return length
}

