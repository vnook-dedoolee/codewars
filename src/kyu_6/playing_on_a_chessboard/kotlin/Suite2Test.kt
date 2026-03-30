package kyu_6.playing_on_a_chessboard.kotlin

import org.junit.Test
import kotlin.test.assertEquals

class Suite2Test {

    @Test
    fun basicTests() {
        assertEquals("[800]", Suite2.game(40))
        assertEquals("[10201, 2]", Suite2.game(101))

    }
}