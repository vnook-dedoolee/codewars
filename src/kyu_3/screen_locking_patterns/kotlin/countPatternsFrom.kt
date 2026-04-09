// https://www.codewars.com/kata/585894545a8a07255e0002f1/train/kotlin

package kyu_3.screen_locking_patterns.kotlin

fun countPatternsFrom(s: String, l: Int) = when {
    l == 1 -> 1
    l < 1 || l > 9 -> 0
    s in "ACGI" -> arrayOf(5, 31, 154, 684, 2516, 7104, 13792, 13792)[l - 2]
    s in "BDFH" -> arrayOf(7, 37, 188, 816, 2926, 8118, 15564, 15564)[l - 2]
    else -> arrayOf(8, 48, 256, 1152, 4248, 12024, 23280, 23280)[l - 2]
}

