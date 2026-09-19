// https://www.codewars.com/kata/5bb5e174528b2908930005b5/train/kotlin

package kyu_6.last_and_rich_in_circle.kotlin

fun findLast(n: Int, m: Int): IntArray {
    val circle = MutableList(n) { it + 1 }
    val coins = IntArray(n)
    var firstIdx = 0

    while (circle.size > 1) {
        val size = circle.size
        val fullRounds = m / size
        val extra = m % size

        for (i in 0 until size) {
            val person = circle[i]
            val relPos = (i - firstIdx + size) % size
            val countTimes = fullRounds + if (relPos < extra) 1 else 0
            coins[person - 1] += if (countTimes > 0) countTimes else 2
        }

        val lastOneCoinIdx = (firstIdx + m - 1) % size
        val leavingPerson = circle[lastOneCoinIdx]
        val nextIdx = (lastOneCoinIdx + 1) % size
        val nextPerson = circle[nextIdx]

        coins[nextPerson - 1] += coins[leavingPerson - 1]
        coins[leavingPerson - 1] = 0

        circle.removeAt(lastOneCoinIdx)
        firstIdx = if (nextIdx > lastOneCoinIdx) nextIdx - 1 else nextIdx
    }

    return intArrayOf(circle[0], coins[circle[0] - 1])
}