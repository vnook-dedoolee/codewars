// https://www.codewars.com/kata/55e86e212fce2aae75000060/train/kotlin

package kyu_6.integers_recreation_two.kotlin

fun prod2Sum(a: Long, b: Long, c: Long, d: Long): List<LongArray> {
    return listOf(
        longArrayOf(a * c + b * d, Math.abs(a * d - b * c)),
        longArrayOf(a * d + b * c, Math.abs(a * c - b * d))
    ).map { it.sortedArray().toList() }
        .toSet()
        .map{it.toLongArray()}
        .sortedBy { it[0] }
}


