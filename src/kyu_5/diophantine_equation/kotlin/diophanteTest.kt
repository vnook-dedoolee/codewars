package kyu_5.diophantine_equation.kotlin

import org.junit.Test
import kotlin.test.assertEquals

class diophanteTest {
    @Test
    fun test2() {
        assertEquals("[[4, 1]]", solEquaStr(12))
    }
    @Test
    fun test3() {
        assertEquals("[[7, 3]]", solEquaStr(13))
    }
    @Test
    fun test11() {
        val a = "[[4505, 2252], [1503, 750], [647, 320], [505, 248], [415, 202], [353, 170], [225, 102], [153, 60], [135, 48], [103, 20], [97, 10], [95, 2]]"
        assertEquals(a, solEquaStr(9009))
    }
}

