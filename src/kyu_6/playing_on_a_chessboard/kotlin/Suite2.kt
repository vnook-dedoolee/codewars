// https://www.codewars.com/kata/55ab4f980f2d576c070000f4/train/kotlin

package kyu_6.playing_on_a_chessboard.kotlin

object Suite2 {
    fun game(n: Long): String {
        val numerator = n * n
        val denominator = 2L
        val gcd = gcd(numerator, denominator)
        val num = numerator / gcd
        val den = denominator / gcd
        return if (den == 1L) "[$num]" else "[$num, $den]"
    }

    private fun gcd(a: Long, b: Long): Long {
        return if (b == 0L) a else gcd(b, a % b)
    }
}
