package kyu_6.simple_fun_393_lonely_frog_V.kotlin

import kotlin.test.assertEquals
import org.junit.Test

class TestExample {
    @Test
    fun `Basic Tests`() {
        assertEquals(true, jumping(intArrayOf(2,3,1,1,4)))
        assertEquals(false, jumping(intArrayOf(3,2,1,0,4)))
        assertEquals(true, jumping(intArrayOf(1,2,0,3,0,0,0)))
        assertEquals(false, jumping(intArrayOf(1,2,0,3,0,0,0,9)))
        assertEquals(false, jumping(intArrayOf(0,1,2,3)))
        assertEquals(true, jumping(intArrayOf(4,0,0,0,1)))
        assertEquals(true, jumping(intArrayOf(9,3,2,1,0,3,2,1,0,1)))
    }
}