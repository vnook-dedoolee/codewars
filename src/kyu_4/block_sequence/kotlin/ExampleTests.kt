package kyu_4.block_sequence.kotlin

import org.junit.Test
import kotlin.test.assertEquals

class ExampleTests {
    private fun runTest(n:Long,sol:Int) = assertEquals(sol,KataSolution.solve(n))

    @Test fun `Basic Test Cases`() {
        runTest(1L,1)
        runTest(2L,1)
        runTest(3L,2)
        runTest(100L,1)
        runTest(2100L,2)
        runTest(31000L,2)
        runTest(999999999999999999L,4)
        runTest(1000000000000000000L,1)
        runTest(999999999999999993L,7)
    }
}