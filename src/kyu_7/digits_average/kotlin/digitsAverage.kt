// https://www.codewars.com/kata/5a32526ae1ce0ec0f10000b2/train/kotlin

package kyu_7.digits_average.kotlin

fun digitsAverage(input: Int): Int {
    val digits = numberToDigits(input)
    return avgEachPair(digits)
}

fun numberToDigits(number: Int): IntArray {
    if (number == 0) return intArrayOf(0)

    val digits = mutableListOf<Int>()
    var n = number

    while (n > 0) {
        digits.add(n % 10)
        n /= 10
    }

    return digits.reversed().toIntArray()
}

fun avgEachPair(digits: IntArray): Int {

    if (digits.size == 1) return digits[0]

    val result = mutableListOf<Int>()

    for (i in digits.indices) {
        if (i < digits.size - 1) {
            val num = digits[i] + digits[i + 1]
            val input = if (num % 2 == 0) num / 2 else (num + 1) / 2
            result.add(input)
        }
    }

    return avgEachPair(result.toIntArray())
}
