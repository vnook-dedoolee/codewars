package kyu_7.alphabetical_grid.kotlin

import org.junit.Test
import kotlin.test.assertEquals

class ExampleTests {
    fun runTest(n: Int, refSol: String?) = assertEquals(refSol,Kata.grid(n))

    @Test fun `Valid tests`() {
        runTest(0, "")
        runTest(1, "a")
        runTest(2, "a b\nb c")
        runTest(4, "a b c d\nb c d e\nc d e f\nd e f g")
        runTest(6, "a b c d e f\nb c d e f g\nc d e f g h\nd e f g h i\ne f g h i j\nf g h i j k")
    }

    @Test fun `Invalid tests`() {
        runTest(-1, null)
        runTest(-5, null)
    }
}