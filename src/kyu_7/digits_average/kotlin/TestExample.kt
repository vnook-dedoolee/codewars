package kyu_7.digits_average.kotlin

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class TestExample {
    @Test
    fun `Sample Tests`() {
        assertEquals(4, digitsAverage(246), "For input: 246")
        assertEquals(9, digitsAverage(89), "For input: 89")
        assertEquals(2, digitsAverage(2), "For input: 2")
        assertEquals(4, digitsAverage(245), "For input: 245")
        assertEquals(5, digitsAverage(345), "For input: 345")
        assertEquals(5, digitsAverage(346), "For input: 346")
    }
}

