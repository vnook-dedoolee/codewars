package kyu_3.screen_locking_patterns.kotlin

import kotlin.test.assertEquals
import org.junit.Test

class SampleTests {
    @Test
    fun `sample tests`() {
        assertEquals(0, countPatternsFrom("A", 10), "Should return 0 for path of length 10 with starting point A")
        assertEquals(0, countPatternsFrom("A", 0), "Should return 0 for path of length 0 with starting point A")
        assertEquals(0, countPatternsFrom("E", 14), "Should return 0 for path of length 14 with starting point E")
        assertEquals(1, countPatternsFrom("B", 1), "Should return 1 for path of length 1 with starting point B")
        assertEquals(5, countPatternsFrom("C", 2), "Should return 5 for path of length 2 with starting point C")
        assertEquals(8, countPatternsFrom("E", 2), "Should return 8 for path of length 2 with starting point E")
        assertEquals(256, countPatternsFrom("E", 4), "Should return 256 for path of length 4 with starting point E")
    }
}
