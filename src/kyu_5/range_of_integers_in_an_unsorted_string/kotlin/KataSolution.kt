// https://www.codewars.com/kata/5b6b67a5ecd0979e5b00000e/train/kotlin

package kyu_5.range_of_integers_in_an_unsorted_string.kotlin

object KataSolution {
    fun mysteryRange(s: String, n: Int): Pair<Int, Int> {
        for (start in 0..99) {
            val end = start + n - 1
            if (dfs(s, 0, start, end, BooleanArray(n))) return Pair(start, end)
        }
        error("no range")
    }

    private fun dfs(s: String, i: Int, start: Int, end: Int, used: BooleanArray): Boolean {
        if (i == s.length) return used.all { it }
        for (l in 1..4) {
            if (i + l > s.length) break
            val v = s.substring(i, i + l).toInt()
            if (v < start || v > end) continue
            val idx = v - start
            if (used[idx]) continue
            used[idx] = true
            if (dfs(s, i + l, start, end, used)) return true
            used[idx] = false
        }
        return false
    }
}