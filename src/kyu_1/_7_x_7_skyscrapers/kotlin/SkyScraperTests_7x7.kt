package kyu_1._7_x_7_skyscrapers.kotlin

import org.junit.Assert.*
import org.junit.Test

class SkyScraperTests_7x7 {
    @Test
    fun solvePuzzle_Medium() {
        val clues = intArrayOf(
            7, 0, 0, 0, 2, 2, 3,
            0, 0, 3, 0, 0, 0, 0,
            3, 0, 3, 0, 0, 5, 0,
            0, 0, 0, 0, 5, 0, 4
        )

        val expected = arrayOf(
            intArrayOf(1, 5, 6, 7, 4, 3, 2),
            intArrayOf(2, 7, 4, 5, 3, 1, 6),
            intArrayOf(3, 4, 5, 6, 7, 2, 1),
            intArrayOf(4, 6, 3, 1, 2, 7, 5),
            intArrayOf(5, 3, 1, 2, 6, 4, 7),
            intArrayOf(6, 2, 7, 3, 1, 5, 4),
            intArrayOf(7, 1, 2, 4, 5, 6, 3)
        )

        val actual = Skyscrapers.solvePuzzle(clues)
        assertArrayEquals(expected, actual)
    }

    @Test
    fun solvePuzzle_VeryHard3() {
        val clues = intArrayOf(
            0, 2, 3, 0, 2, 0, 0,
            5, 0, 4, 5, 0, 4, 0,
            0, 4, 2, 0, 0, 0, 6,
            5, 2, 2, 2, 2, 4, 1
        ) // NOTE: for a VERY HARD puzzle, replace the last 7 values with zeroes

        val expected = arrayOf(
            intArrayOf(7, 6, 2, 1, 5, 4, 3),
            intArrayOf(1, 3, 5, 4, 2, 7, 6),
            intArrayOf(6, 5, 4, 7, 3, 2, 1),
            intArrayOf(5, 1, 7, 6, 4, 3, 2),
            intArrayOf(4, 2, 1, 3, 7, 6, 5),
            intArrayOf(3, 7, 6, 2, 1, 5, 4),
            intArrayOf(2, 4, 3, 5, 6, 1, 7)
        )

        val actual = Skyscrapers.solvePuzzle(clues)
        assertArrayEquals(expected, actual)
    }
}