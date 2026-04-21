// https://www.codewars.com/kata/554f76dca89983cc400000bb/train/kotlin

package kyu_5.diophantine_equation.kotlin

fun solEquaStr(n: Long): String {
    val solutions = mutableListOf<Pair<Long, Long>>()

    var a = 1L
    while (a * a <= n) {
        if (n % a == 0L) {
            val b = n / a

            checkPair(a, b, solutions)
            if (a != b) {
                checkPair(b, a, solutions)
            }
        }
        a++
    }

    solutions.sortByDescending { it.first }

    return solutions.joinToString(", ", "[", "]") { "[${it.first}, ${it.second}]" }
}

private fun checkPair(d1: Long, d2: Long, solutions: MutableList<Pair<Long, Long>>) {

    val sum = d1 + d2
    val diff = d2 - d1

    if (sum % 2 == 0L && diff % 4 == 0L) {
        val x = sum / 2
        val y = diff / 4

        if (x >= 0 && y >= 0) {
            solutions.add(Pair(x, y))
        }
    }
}