package kyu_5.range_of_integers_in_an_unsorted_string.kotlin

import org.junit.Test
import kotlin.test.assertEquals

class ExampleTests {
    private fun runTest(s: String, n: Int, sol: Pair<Int, Int>) = assertEquals(sol, KataSolution.mysteryRange(s, n))

    @Test
    fun `Example Tests`() {
        runTest("1568141291110137", 10, Pair(6, 15))
        runTest("6291211413114538107", 14, Pair(1, 14))
        runTest("13161820142119101112917232215", 15, Pair(9, 23))
        runTest("2318134142120517221910151678611129", 20, Pair(4, 23))
        runTest("10610211511099104113100116105103101111114107108112109", 18, Pair(99, 116))
        runTest(
            "1721532418565922162558663126649136347436733301144143236653738464135820194215516155541239452852623450572927602348104049",
            60,
            Pair(8, 67)
        )
    }
}