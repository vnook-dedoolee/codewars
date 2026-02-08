package kyu_6.sorting_by_exclusion.kotlin

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SortByExclusionTesting {


    @Test
    fun testRegularCases() {
        assertEquals(1, sortByExclusion(arrayOf("M", "O", "A"))) // "Remove A"
        assertEquals(1, sortByExclusion(arrayOf("M", "O", "Q", "A"))) // "Remove A"

        assertEquals(2, sortByExclusion(arrayOf("M", "O", "A", "C"))) // "Remove A, C (or M, O)"
        assertEquals(2, sortByExclusion(arrayOf("M", "A", "O", "C"))) // "Remove A, C (or M, O)"
        assertEquals(2, sortByExclusion(arrayOf("M", "O", "Q", "A", "C"))) // "Remove A, C"
        assertEquals(2, sortByExclusion(arrayOf("M", "O", "A", "C", "E"))) // "Remove M, O"

        assertEquals(3, sortByExclusion(arrayOf("M", "O", "Q", "A", "C", "E"))) // "Remove A, C, E (or M, O, Q)"
        assertEquals(3, sortByExclusion(arrayOf("M", "O", "N", "A", "C", "E"))) // "Remove M, O, N"
        assertEquals(3, sortByExclusion(arrayOf("M", "O", "Q", "A", "C", "E", "G"))) // "Remove M, O, Q"
        assertEquals(3, sortByExclusion(arrayOf("M", "O", "Q", "A", "C", "E", "G", "I", "K", "M"))) // "Remove M, O, Q"

        assertEquals(4, sortByExclusion(arrayOf("N", "M", "O", "N", "A", "C", "E"))) // "Remove N, M, O, N"
        assertEquals(4, sortByExclusion(arrayOf("M", "O", "Q", "A", "C", "E", "G", "I", "L", "K", "M"))) // "Remove M, O, N, K"

        assertEquals(3, sortByExclusion(arrayOf("ANT", "CAT", "DOG", "BEE", "DOLPHIN", "ZEBRA", "LION", "EAGLE"))) // "Remove BEE, LION & EAGLE"
        assertEquals(2, sortByExclusion(arrayOf("ANT", "CAT", "DOG", "BEE", "DOLPHIN", "ZEBRA", "EAGLE", "LION"))) // "Remove BEE & ZEBRA"
        assertEquals(4, sortByExclusion(arrayOf("FOX", "ANT", "CAT", "DOG", "BEE", "DOLPHIN", "ZEBRA", "EAGLE", "LION", "FOX"))) // "Remove FOX, BEE, ZEBRA & FOX"
    }

    @Test
    fun testEdgeCases() {
        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

        // Input is ABC...XYZ, so all elements are in order
        assertEquals(0, sortByExclusion(alphabet.map { it.toString() }.toTypedArray()))

        // Input is XYZ...ABC, so must remove all but one
        assertEquals(25, sortByExclusion(alphabet.reversed().map { it.toString() }.toTypedArray()))

        // Input is 99 equal elements, so must remove all but one
        val ninetyNineMs = Array(99) { "M" }
        assertEquals(98, sortByExclusion(ninetyNineMs))

        // One element list
        assertEquals(0, sortByExclusion(arrayOf("M")))
    }
}
