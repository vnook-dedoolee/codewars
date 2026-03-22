// https://www.codewars.com/kata/5e1ab1b9fe268c0033680e5f/train/kotlin

package kyu_4.block_sequence.kotlin

object KataSolution {
    fun solve(n: Long): Int = forge(n, 9L, 1L, 0L, 0L)

    private val proc = { q: Long, d: Long, ct: Long -> (q + d + q + d * ct) * ct / 2L }

    private tailrec fun forge(n: Long, ct: Long, d: Long, q: Long, c: Long): Int {
        val m = proc(q, d, ct)
        return if (c + m >= n) binseek(n - c, d, q, p10(d - 1L), p10(d) - 1L) else forge(
            n,
            ct * 10L,
            d + 1L,
            q + d * ct,
            c + m
        )
    }

    private tailrec fun binseek(n: Long, d: Long, q: Long, q0: Long, q1: Long): Int {
        val mp = (q1 - q0 + 1L) / 2L
        if (mp < 1L) return digitseek(n - 1L, 1L, 9L)
        val dc = proc(q, d, mp)
        val args = if (dc >= n) arrayOf(n, q, q0, q0 + mp - 1L) else arrayOf(n - dc, q + d * mp, q0 + mp, q1)
        return binseek(args[0], d, args[1], args[2], args[3])
    }

    private tailrec fun digitseek(n: Long, d: Long, z: Long): Int {
        if (n <= 0L) return 1
        val v = d * z
        return if (n <= v) (n / d + p10(d - 1L)).toString()[(n % d).toInt()].toInt() - 48 else digitseek(
            n - v,
            d + 1L,
            z * 10L
        )
    }

    private tailrec fun p10(n: Long, v: Long = 1L): Long = if (n < 1L) v else p10(n - 1L, v * 10L)
}
