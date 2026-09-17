// https://www.codewars.com/kata/595467c63074e38ba4000063/train/kotlin

package kyu_5.simple_fun_333_incomplete_virus.kotlin

object Kata {
    fun incompleteVirus(s: String): Long {
        val L = s.length
        var count = (1L shl (L - 1)) - 1L

        for (i in 0 until L) {
            val c = s[i]
            if (c > '1') {
                count += if (i == 0) 1L shl (L - 1) else 1L shl (L - i)
                return count
            }
            if (i > 0 && c == '1') {
                count += 1L shl (L - 1 - i)
            }
        }
        return count + 1
    }
}