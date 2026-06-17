// https://www.codewars.com/kata/640aa37b431f2da51c7f27ae/train/kotlin

package kyu_6.a_fun_way_of_finding_pythagorean_triples.kotlin

fun createPythagoreanTriples(diff: Int, low: Int, high: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    for (a in low..high) {
        val aSq = a.toLong() * a
        if (aSq % diff != 0L) continue

        val sum = aSq / diff
        if ((sum - diff) % 2 != 0L) continue

        val b = ((sum - diff) / 2).toLong()
        val c = ((sum + diff) / 2).toLong()

        if (b <= 0 || c <= 0) continue
        if (a >= b || b >= c) continue
        if (b > Int.MAX_VALUE || c > Int.MAX_VALUE) continue

        result.add(listOf(a, b.toInt(), c.toInt()))
    }

    return result
}
