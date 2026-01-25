package kyu_6.divisible_by_primes.kotlin

import junit.framework.TestCase.assertEquals
import org.junit.Test

class TestExample {
    @Test
    fun example() {
        assertEquals("1-sum", solve(3))
        assertEquals("3-altsum", solve(7))
        assertEquals("1-altsum", solve(11))
        assertEquals("3-altsum", solve(13))
        assertEquals("3-sum", solve(37))
        assertEquals("23-altsum", solve(47))
        assertEquals("4-altsum", solve(73))
        assertEquals("7-sum", solve(239))
        assertEquals("47006-altsum", solve(376049))
        assertEquals("499941-sum", solve(999883))
        assertEquals("12350861-sum", solve(24701723))
        assertEquals("11484850-altsum", solve(45939401))
    }
}