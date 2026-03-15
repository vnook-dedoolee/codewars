package kyu_7.simple_fun_8_kill_kth_bit.kotlin

import kotlin.test.assertEquals
import org.junit.Test

class TestExample {
    @Test
    fun `Basic Tests`() {
        assertEquals(33, killKthBit(37,3))
        assertEquals(37, killKthBit(37,4))
        assertEquals(0, killKthBit(0,4))
        assertEquals(2147450879, killKthBit(2147483647,16))
        assertEquals(374819652, killKthBit(374823748,13))
        assertEquals(1084197039, killKthBit(1084197039,15))
    }
}

