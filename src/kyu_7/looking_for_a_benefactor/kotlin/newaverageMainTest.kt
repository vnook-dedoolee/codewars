package kyu_7.looking_for_a_benefactor.kotlin

import org.junit.Assert.*
import org.junit.Test
import kotlin.test.assertEquals
import java.util.Random

class newaverageMainTest {
    private fun testing(actual:Long, expected:Long) {
        assertEquals(expected, actual)
    }
    @Test
    fun test() {
        println("Fixed Tests newAvg")
        var a = doubleArrayOf(14.0, 30.0, 5.0, 7.0, 9.0, 11.0, 16.0)
        testing(newAvg(a, 90.0), 628)
        a = doubleArrayOf(14.0, 30.0, 5.0, 7.0, 9.0, 11.0, 15.0)
        testing(newAvg(a, 92.0), 645)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testIllegalArgumentException1() {
        println("--- Exception 1 ---")
        val a = doubleArrayOf(14.0, 30.0, 5.0, 7.0, 9.0, 11.0, 15.0)
        newAvg(a, 2.0)
    }

}