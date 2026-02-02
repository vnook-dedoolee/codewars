// https://www.codewars.com/kata/5d2659626c7aec0022cb8006/train/kotlin

package kyu_7.the_baum_sweet_sequence.kotlin

fun baumSweet(): Iterator<Int> = iterator {
    var n = 0

    while (true) {
        yield(calcBaumSweet(n))
        n++
    }
}

private fun calcBaumSweet(n: Int): Int {
    if (n == 0) return 1

    val binary = n.toString(2)
    var zeros = 0
    var isZerosBlock = false

    for (bit in binary) {
        if (bit == '0') {
            zeros++
            isZerosBlock = true
        } else {
            if (isZerosBlock && zeros % 2 == 1) {
                return 0
            }
            zeros = 0
            isZerosBlock = false
        }
    }

    if (isZerosBlock && zeros % 2 == 1) {
        return 0
    }

    return 1
}