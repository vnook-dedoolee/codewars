package kyu_6.framed_reflection.kotlin

import kotlin.test.assertEquals
import org.junit.Test

class TestExample {
    @Test
    fun `Basic Tests`() {
        assertEquals("*********\n* olleH *\n* dlroW *\n*********", mirror("Hello World"))
        assertEquals("************\n* srawedoC *\n************", mirror("Codewars"))
    }
}
