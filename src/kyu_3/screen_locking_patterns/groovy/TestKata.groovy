package kyu_3.screen_locking_patterns.groovy

import org.junit.Test

class TestKata {
    @Test
    void basicTests() {
        assert(Kata.countPatterns('A', 0) == 0)
        assert(Kata.countPatterns('A', 10) == 0)
        assert(Kata.countPatterns('B', 1) == 1)
        assert(Kata.countPatterns('C', 2) == 5)
        assert(Kata.countPatterns('D', 3) == 37)
        assert(Kata.countPatterns('E', 4) == 256)
        assert(Kata.countPatterns('E', 8) == 23280)
    }
}
