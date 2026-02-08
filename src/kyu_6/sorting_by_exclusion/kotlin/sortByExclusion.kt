// https://www.codewars.com/kata/63bcd25eaeeb6a3b48a72dca/train/kotlin

package kyu_6.sorting_by_exclusion.kotlin

fun sortByExclusion(words: Array<String>): Int {
    val n = words.size
    if (n <= 1) return 0

    val dp = IntArray(n) { 1 }

    for (i in 1 until n) {
        for (j in 0 until i) {
            if (words[j] < words[i]) {
                dp[i] = maxOf(dp[i], dp[j] + 1)
            }
        }
    }

    return n - (dp.maxOrNull() ?: 1)
}
