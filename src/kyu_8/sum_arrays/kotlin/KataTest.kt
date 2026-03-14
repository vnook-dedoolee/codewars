package kyu_8.sum_arrays.kotlin

import kotlin.test.assertEquals
import org.junit.Test

class KataTest {

    @Test
    fun `sample test`() {

        assertEquals(9.2, Kata.sumArray(doubleArrayOf(1.0, 5.2, 4.0, 0.0, -1.0)))
        assertEquals(0.0, Kata.sumArray(doubleArrayOf()))
    }
}
