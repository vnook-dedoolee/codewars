package kyu_6.bankers_plan.kotlin

import org.junit.Assert.*
import org.junit.Test

class BankerPlanTest {
    @Test
    fun test1() {
        println("Fixed Tests: fortune")
        testing(fortune(100000, 1.0, 2000, 15, 1.0), true)
        testing(fortune(100000, 1.0, 9185, 12, 1.0), false)

    }

    companion object {
        private fun testing(actual: Boolean, expected: Boolean) {
            assertEquals(expected, actual)
        }
    }
}