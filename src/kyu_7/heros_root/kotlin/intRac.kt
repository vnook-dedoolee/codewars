// https://www.codewars.com/kata/55efecb8680f47654c000095/train/kotlin

package kyu_7.heros_root.kotlin

fun intRac(n: Long, guess: Long): Long {
    var x = guess
    var count = 1L

    while (true) {
        val next = (x + n / x) / 2
        if (kotlin.math.abs(x - next) < 1) {
            return count
        }
        x = next
        count++
    }
}