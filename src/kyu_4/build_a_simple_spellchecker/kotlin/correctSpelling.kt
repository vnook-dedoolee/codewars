// https://www.codewars.com/kata/68192cd301fbd97e7191f876/train/kotlin

package kyu_4.build_a_simple_spellchecker.kotlin

fun correctSpelling(text: String, wordList: Array<String>): Map<String, List<String>> {

    val lowerWordList = wordList.map { it.lowercase() }
    val lowerWordSet = lowerWordList.toHashSet()

    val tokens = text.split(Regex("\\s+"))
    val result = mutableMapOf<String, List<String>>()

    for (token in tokens) {
        val cleaned = stripOuterPunctuation(token)
        if (cleaned.isEmpty()) continue

        val cleanedLower = cleaned.lowercase()
        if (cleanedLower in lowerWordSet) continue

        val dist1 = lowerWordList.filter { damerauLevenshteinWithCutoff(cleanedLower, it, 1) == 1 }

        val candidates =
            if (dist1.isNotEmpty()) dist1
            else lowerWordList.filter { damerauLevenshteinWithCutoff(cleanedLower, it, 2) == 2 }

        val corrected = candidates.map { matchCase(cleaned, it) }.distinct().sorted()

        result[cleaned] = corrected
    }

    return result
}

private fun stripOuterPunctuation(s: String): String {
    var start = 0
    var end = s.length - 1

    while (start <= end && !s[start].isLetter()) start++
    while (end >= start && !s[end].isLetter()) end--

    return if (start > end) "" else s.substring(start, end + 1)
}

private fun matchCase(misspelled: String, correctLower: String): String {

    val anyLetter = misspelled.any { it.isLetter() }
    val allUpper =
        anyLetter &&
                misspelled.filter { it.isLetter() }.all { it.isUpperCase() } &&
                misspelled.length > 1

    if (allUpper) return correctLower.uppercase()

    // Length 1 → keep the case
    if (misspelled.length == 1) {
        val m = misspelled[0]
        return if (m.isUpperCase()) correctLower.replaceFirstChar { it.uppercaseChar() }
        else correctLower.replaceFirstChar { it.lowercaseChar() }
    }

    val result = correctLower.toCharArray()
    val n = minOf(misspelled.length, correctLower.length)

    for (i in 0 until n) {
        val mc = misspelled[i]
        if (!mc.isLetter()) continue

        if (mc.lowercaseChar() == result[i]) {
            result[i] = if (mc.isUpperCase()) result[i].uppercaseChar()
            else result[i].lowercaseChar()
        }
    }

    return String(result)
}

fun damerauLevenshteinWithCutoff(s1: String, s2: String, maxDist: Int): Int {

    val len1 = s1.length
    val len2 = s2.length

    if (kotlin.math.abs(len1 - len2) > maxDist) return maxDist + 1

    val maxVal = len1 + len2
    val dp = Array(len1 + 2) { IntArray(len2 + 2) }

    val last = mutableMapOf<Char, Int>()
    (s1 + s2).forEach { last.putIfAbsent(it, 0) }

    dp[0][0] = maxVal

    for (i in 0..len1) {
        dp[i + 1][0] = maxVal
        dp[i + 1][1] = i
    }
    for (j in 0..len2) {
        dp[0][j + 1] = maxVal
        dp[1][j + 1] = j
    }

    for (i in 1..len1) {
        var lastSeen = 0
        for (j in 1..len2) {

            val i1 = last[s2[j - 1]] ?: 0
            val j1 = lastSeen

            val cost = if (s1[i - 1] == s2[j - 1]) 0 else 1
            if (cost == 0) lastSeen = j

            dp[i + 1][j + 1] = minOf(
                dp[i][j] + cost,
                dp[i + 1][j] + 1,
                dp[i][j + 1] + 1,
                dp[i1][j1] + (i - i1 - 1) + 1 + (j - j1 - 1)
            )
        }
        last[s1[i - 1]] = i
    }

    val dist = dp[len1 + 1][len2 + 1]
    return if (dist <= maxDist) dist else maxDist + 1
}

