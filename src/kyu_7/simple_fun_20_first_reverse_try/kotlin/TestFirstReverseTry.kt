package kyu_7.simple_fun_20_first_reverse_try.kotlin

import org.junit.Assert.*;
import org.junit.Test

class TestFirstReverseTry {
    @Test
    fun `Basic Tests`() {
        assertArrayEquals(intArrayOf(5,2,3,4,1), firstReverseTry(intArrayOf(1,2,3,4,5)))
        assertArrayEquals(intArrayOf(), firstReverseTry(intArrayOf()))
        assertArrayEquals(intArrayOf(111), firstReverseTry(intArrayOf(111)))
        assertArrayEquals(intArrayOf(324, 54, 12, 3, 4, 56, 23, 12, 5, 23), firstReverseTry(intArrayOf(23, 54, 12, 3, 4, 56, 23, 12, 5, 324)))
        assertArrayEquals(intArrayOf(1,-1), firstReverseTry(intArrayOf(-1,1)))
    }
}