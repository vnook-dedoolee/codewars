// https://www.codewars.com/kata/5966a52ab4f24db1800000cc/train/kotlin

package kyu_6.a_plus_b_is_123.kotlin

object Dinglemouse {
    fun int123(a: Int): Long {
        val diff = 123L - a
        return if (diff >= 0) diff else diff + (1L shl 32)
    }
}
