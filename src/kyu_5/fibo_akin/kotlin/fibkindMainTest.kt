package kyu_5.fibo_akin.kotlin

import org.junit.Assert.*
import org.junit.Test

class fibkindMainTest {
    private fun dotest1(n: Int, k: Int, expected: Long) {
        assertEquals(expected, lengthSupUK(n, k))
    }

    private fun dotest2(n: Int, expected: Long) {
        assertEquals(expected, comp(n))
    }

    @Test
    fun test1() {
        println("Basic Tests lengthSupUK")
        dotest1(50, 25, 2)
        dotest1(3332, 973, 1391)

    }

    @Test
    fun test2() {
        println("Basic Tests comp")
        dotest2(74626, 37128)
        dotest2(71749, 35692)

    }
}
