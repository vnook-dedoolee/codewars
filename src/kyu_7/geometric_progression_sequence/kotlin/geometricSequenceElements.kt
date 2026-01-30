// https://www.codewars.com/kata/55caef80d691f65cb6000040/train/kotlin

package kyu_7.geometric_progression_sequence.kotlin

fun geometricSequenceElements(a: Int, r: Int, n: Int): String {
    if (n <= 0) return ""
    val result = mutableListOf<Int>()
    var dig = a
    for (i in 1 .. n) {
        result.add(dig)
        dig *= r
    }
    return result.joinToString(", ")
}