// https://www.codewars.com/kata/5993c1d917bc97d05d000068/train/kotlin

package kyu_1.regular_expression_for_binary_numbers_divisible_by_n.kotlin


fun regexDivisibleBy(n: Int): String {
    var r = Array(n) { Array(n) { "" } }
    for (i in 0 until n) 
        for (j in 0 until n) {
            var t = ""
            for (d in 0..1) 
                if ((i * 2 + d) % n == j) 
                    t = if (t.isEmpty()) "$d" else "(0|1)"
            r[i][j] = t
        }

    for (k in 0 until n) {
        val nr = Array(n) { Array(n) { "" } }
        for (i in 0 until n) 
            for (j in 0 until n) {
                var mid = ""
                val b = r[i][k]; val c = r[k][k]; val d = r[k][j]
                if (b.isNotEmpty() && d.isNotEmpty())
                    mid = if (c.isEmpty()) "($b$d)" else "($b($c)*$d)"
                val a = r[i][j]
                nr[i][j] = when {
                    a.isEmpty() -> mid
                    mid.isEmpty() -> a
                    else -> "($a|$mid)"
                }
            }
        r = nr
    }
    return "^" + r[0][0] + "$"
}
