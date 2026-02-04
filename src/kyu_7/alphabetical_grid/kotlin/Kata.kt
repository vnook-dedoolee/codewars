// https://www.codewars.com/kata/60a94f1443f8730025d1744b/train/kotlin

package kyu_7.alphabetical_grid.kotlin

object Kata {
    fun grid(n: Int): String? {
        if (n < 0) return null
        val result = StringBuilder()
        for (row in 0 until n) {
            for (col in 0 until n) {
                val charIndex = (row + col) % 26
                val letter = 'a' + charIndex
                result.append(letter).append(' ')
            }
            result.deleteAt(result.length - 1)
            result.append("\n")
        }
        return result.toString().trimEnd()
    }
}