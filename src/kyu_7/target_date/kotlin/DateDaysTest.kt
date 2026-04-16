package kyu_7.target_date.kotlin

import org.junit.Assert.*
import org.junit.Test

class DateDaysTest {
    private fun testing(actual: String, expected: String) {
        assertEquals(expected, actual)
    }

    @Test
    fun test() {
        println("Fixed Tests dateNbDays")
        testing(dateNbDays(4281.0, 5087.0, 2.0), "2024-07-03")
        testing(dateNbDays(4620.0, 5188.0, 2.0), "2021-09-19")

    }
}