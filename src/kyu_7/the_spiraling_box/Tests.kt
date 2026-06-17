package kyu_7.the_spiraling_box

import org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.Test

class Tests {
    @Test
    fun sampleTests() {
        val box_7_8 = arrayOf(
            intArrayOf(1, 1, 1, 1, 1, 1, 1),
            intArrayOf(1, 2, 2, 2, 2, 2, 1),
            intArrayOf(1, 2, 3, 3, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 3, 3, 2, 1),
            intArrayOf(1, 2, 2, 2, 2, 2, 1),
            intArrayOf(1, 1, 1, 1, 1, 1, 1)
        )

        val box_8_7 = arrayOf(
            intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
            intArrayOf(1, 2, 2, 2, 2, 2, 2, 1),
            intArrayOf(1, 2, 3, 3, 3, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 3, 3, 3, 2, 1),
            intArrayOf(1, 2, 2, 2, 2, 2, 2, 1),
            intArrayOf(1, 1, 1, 1, 1, 1, 1, 1)
        )

        val box_4_2 = arrayOf(intArrayOf(1, 1, 1, 1), intArrayOf(1, 1, 1, 1))
        val box_2_4 = arrayOf(intArrayOf(1, 1), intArrayOf(1, 1), intArrayOf(1, 1), intArrayOf(1, 1))

        assertArrayEquals(box_7_8, createBox(7, 8))
        assertArrayEquals(box_8_7, createBox(8, 7))
        assertArrayEquals(box_4_2, createBox(4, 2))
        assertArrayEquals(box_2_4, createBox(2, 4))
    }
}
