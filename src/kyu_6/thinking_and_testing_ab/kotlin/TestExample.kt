package kyu_6.thinking_and_testing_ab.kotlin

import kotlin.test.assertEquals
import org.junit.Test

class TestExample {
    @Test
    fun testFixed() {
        //Hmm.. 0 * 1 = 0
        assertEquals(0, testIt(0,1))
        //Yes, 1 * 2 = 2
        assertEquals(2, testIt(1,2))
        //I know, 5 * 6 = 30
        assertEquals(30, testIt(5,6))
        //What? 10 * 10 = 1 ?
        assertEquals(1, testIt(10,10))
        //Damn.. 200 * 200 = 4, 0 was omitted ?
        assertEquals(4, testIt(200,200))
        //Discover the mysteries of it ;-)
        assertEquals(21, testIt(12,34))
        //You can solve it
        assertEquals(54, testIt(123,45))
    }
}

