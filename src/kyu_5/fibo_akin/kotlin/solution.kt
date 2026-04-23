// https://www.codewars.com/kata/5772382d509c65de7e000982/train/kotlin

package kyu_5.fibo_akin.kotlin

fun lengthSupUK(n: Int, k: Int): Long {
    val u = generateSequence(n)
    return u.take(n).count { it >= k }.toLong()
}

fun comp(n: Int): Long {
    val u = generateSequence(n)
    return u.take(n).zipWithNext().count { (prev, curr) -> curr < prev }.toLong()
}

private fun generateSequence(limit: Int): List<Int> {
    val u = mutableListOf(1, 1)
    for (i in 2 until limit) {
        val next = u[i - u[i - 1]] + u[i - u[i - 2]]
        u.add(next)
    }
    return u
}