package kyu_5.simple_fun_333_incomplete_virus.kotlin

import org.junit.Test
import kotlin.test.assertEquals

class ExampleTests {
    fun runTest(s: String, sol: Long) = assertEquals(sol,Kata.incompleteVirus(s))

    @Test fun `Sample tests`() {
        runTest("10",2)
        runTest("20",3)
        runTest("72",3)
        runTest("99",3)
        runTest("100",4)
        runTest("101",5)
        runTest("102",5)
        runTest("1000000000000000000",262144)
    }
}