// https://www.codewars.com/kata/5cfe4465ac68b86026b09c77/train/kotlin

package kyu_6.divisible_by_primes.kotlin

fun Long.pow(e: Long, m: Long): Long = when {
    e == 0L -> 1
    e % 2 == 0L -> (this * this % m).pow(e / 2, m)
    else -> this * this.pow(e - 1, m) % m
}

fun solve(p: Long): String {
    var d2 = -1L
    for (d in 1..Math.sqrt(p.toDouble()).toLong()) {
        if ((p - 1) % d == 0L) {
            when (10L.pow(d, p)) {
                1L -> return "$d-sum"
                p - 1 -> return "$d-altsum"
            }
            val t = (p - 1) / d
            when (10L.pow(t, p)) {
                1L, p - 1 -> d2 = t
            }
        }
    }
    return "$d2-${if (10L.pow(d2, p) == 1L) "sum" else "altsum"}"
}