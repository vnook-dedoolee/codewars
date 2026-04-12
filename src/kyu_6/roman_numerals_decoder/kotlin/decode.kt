// https://www.codewars.com/kata/51b6249c4612257ac0000005/train/kotlin

package kyu_6.roman_numerals_decoder.kotlin

fun decode(roman: String): Int {
    val values = mapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000)
    var prev = 0

    return roman.foldRight(0) { ch, acc ->
        val curr = values[ch] ?: 0
        (acc + if (curr < prev) -curr else curr).also { prev = curr }
    }
}