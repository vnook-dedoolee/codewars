package kyu_6.tricky_kotlin_4_prevent_stackoverflow.kotlin

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

class KotlinTricks4 {
    @Test
    fun loopTest() {
        fun loopTester(random: Random, int: Int): Int {
            (1..int).forEach { random.nextInt() }
            return random.nextInt()
        }
        (0..100).forEach {
            val i = System.currentTimeMillis()
            assertEquals(loopTester(Random(i), 10000), loop(Random(i), 10000))
        }
    }
}