package kyu_6.integers_recreation_two.kotlin

import org.junit.Assert.*
import org.junit.Test
import java.util.ArrayList
import java.util.Arrays

class sqprod2sumMainTest {
    fun compare(exp:List<LongArray>, act:List<LongArray>) {
        assertTrue("Arrays not the same length", exp.size == act.size)
        var sexp = ""
        for (r in exp)
        {
            sexp += Arrays.toString(r) + " "
        }
        sexp.trim({ it <= ' ' })
        var sact = ""
        for (r in exp)
        {
            sact += Arrays.toString(r) + " "
        }
        sact.trim({ it <= ' ' })
        assertEquals(sexp, sact)
    }
    @Test
    fun test0() {
        var exp = ArrayList<LongArray>()
        var cc = longArrayOf(1, 7)
        exp.add(cc)
        var dd = longArrayOf(5, 5)
        exp.add(dd)
        assertArrayEquals(exp.get(0), prod2Sum(1, 2, 1, 3).get(0))
        assertArrayEquals(exp.get(1), prod2Sum(1, 2, 1, 3).get(1))
        exp = ArrayList<LongArray>()
        cc = longArrayOf(2, 23)
        exp.add(cc)
        dd = longArrayOf(7, 22)
        exp.add(dd)
        assertArrayEquals(exp.get(0), prod2Sum(2, 3, 4, 5).get(0))
        assertArrayEquals(exp.get(1), prod2Sum(2, 3, 4, 5).get(1))
    }

}
