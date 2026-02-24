// https://www.codewars.com/kata/56e3cd1d93c3d940e50006a4/train/kotlin

package kyu_7.how_green_is_my_valley.kotlin

fun makeValley(arr: IntArray): IntArray {
    val sorted = arr.sorted()
    val descending = (sorted.size - 1 downTo 0 step 2).map { sorted[it] }
    val ascending = sorted.toMutableList().apply {
        (sorted.size - 1 downTo 0 step 2).forEach { removeAt(it) }
    }.sorted()
    return (descending + ascending).toIntArray()
}


