// https://www.codewars.com/kata/582c6b7cfb3179eb6e000027/train/kotlin

package kyu_6.zig_zag_camel.kotlin

import kotlin.math.hypot
import kotlin.math.min

fun zigZagCamel(d: Double, h: Double): Double {
    return if (h / d > 1.0 / Math.sqrt(3.0)) {
        2.0 * min(d, h)
    } else {
        hypot(d, h)
    }
}
