package kyu_6.address_book_by_state.kotlin

import org.junit.Test
import kotlin.test.assertEquals

class StateTest {
    private fun testing(s: String, exp: String) {
        val ans = State.byState(s)
        assertEquals(exp, ans)
    }
    @Test
    fun basicTest() {
        val ad2 = "John Daggett, 341 King Road, Plymouth MA\n" +
                "Alice Ford, 22 East Broadway, Richmond VA\n" +
                "Orville Thomas, 11345 Oak Bridge Road, Tulsa OK\n" +
                "Terry Kalkas, 402 Lans Road, Beaver Falls PA\n" +
                "Eric Adams, 20 Post Road, Sudbury MA\n" +
                "Hubert Sims, 328A Brook Road, Roanoke MA\n" +
                "Amy Wilde, 334 Bayshore Pkwy, Mountain View CA\n" +
                "Sal Carpenter, 73 6th Street, Boston MA"
        val ad0 = "John Daggett, 341 King Road, Plymouth MA\n" +
                "Alice Ford, 22 East Broadway, Richmond VA\n" +
                "Orville Thomas, 11345 Oak Bridge Road, Tulsa OK\n" +
                "Terry Kalkas, 402 Lans Road, Beaver Falls PA\n" +
                "Eric Adams, 20 Post Road, Sudbury MA\n" +
                "Hubert Sims, 328A Brook Road, Roanoke VA\n" +
                "Amy Wilde, 334 Bayshore Pkwy, Mountain View CA\n" +
                "Sal Carpenter, 73 6th Street, Boston MA"
        var a =
            "California\n..... Amy Wilde 334 Bayshore Pkwy Mountain View California\n Massachusetts\n..... Eric Adams 20 Post Road Sudbury Massachusetts\n..... Hubert Sims 328A Brook Road Roanoke Massachusetts\n..... John Daggett 341 King Road Plymouth Massachusetts\n..... Sal Carpenter 73 6th Street Boston Massachusetts\n Oklahoma\n..... Orville Thomas 11345 Oak Bridge Road Tulsa Oklahoma\n Pennsylvania\n..... Terry Kalkas 402 Lans Road Beaver Falls Pennsylvania\n Virginia\n..... Alice Ford 22 East Broadway Richmond Virginia"
        testing(ad2, a)
        a =
            "California\n..... Amy Wilde 334 Bayshore Pkwy Mountain View California\n Massachusetts\n..... Eric Adams 20 Post Road Sudbury Massachusetts\n..... John Daggett 341 King Road Plymouth Massachusetts\n..... Sal Carpenter 73 6th Street Boston Massachusetts\n Oklahoma\n..... Orville Thomas 11345 Oak Bridge Road Tulsa Oklahoma\n Pennsylvania\n..... Terry Kalkas 402 Lans Road Beaver Falls Pennsylvania\n Virginia\n..... Alice Ford 22 East Broadway Richmond Virginia\n..... Hubert Sims 328A Brook Road Roanoke Virginia"
        testing(ad0, a)

    }
}