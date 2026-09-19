package kyu_6.last_and_rich_in_circle.kotlin

import org.junit.Test
import org.junit.Assert.*;

class TestFindLast {
    @Test
    fun `Basic tests`() {
        assertArrayEquals(intArrayOf(35,4238), findLast(75, 34))
        assertArrayEquals(intArrayOf(48,5091), findLast(82, 49))
        assertArrayEquals(intArrayOf(61,3996), findLast(73, 38))
        assertArrayEquals(intArrayOf(10,6275), findLast(86, 71))
        assertArrayEquals(intArrayOf(26,3000), findLast(61, 17))
        assertArrayEquals(intArrayOf(12,1578), findLast(42, 38))
        assertArrayEquals(intArrayOf(28,740), findLast(29, 5))
        assertArrayEquals(intArrayOf(43,3327), findLast(64, 49))
        assertArrayEquals(intArrayOf(32,2922), findLast(61, 20))
        assertArrayEquals(intArrayOf(59,5856), findLast(88, 52))
        assertArrayEquals(intArrayOf(2,6), findLast(3, 3))
        assertArrayEquals(intArrayOf(2,8), findLast(3, 4))
    }
}
