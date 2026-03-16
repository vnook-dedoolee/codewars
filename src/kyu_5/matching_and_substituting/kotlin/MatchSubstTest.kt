package kyu_5.matching_and_substituting.kotlin

import org.junit.Test
import kotlin.test.assertEquals

class MatchSubstTest {
    private fun dotest(s: String, prog: String, version: String, exp: String) {
        val ans = MatchSubst.change(s, prog, version)
        assertEquals(exp, ans)
    }
    @Test
    fun test() {
        println("Basic Tests");
        val s1 = "Program title: Primes\nAuthor: Kern\nCorporation: Gold\nPhone: +1-503-555-0091\nDate: Tues April 9, 2005\nVersion: 6.7\nLevel: Alpha"
        dotest(s1, "Ladder", "1.1", "Program: Ladder Author: g964 Phone: +1-503-555-0090 Date: 2019-01-01 Version: 1.1")

        val s14 = "Program title: Primes\nAuthor: Kern\nCorporation: Gold\nPhone: +1-503-555-0091\nDate: Tues April 9, 2005\nVersion: 6.7.5\nLevel: Alpha"
        dotest(s14, "Ladder", "1.1", "ERROR: VERSION or PHONE")

    }
}