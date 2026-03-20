package kyu_6.regex_tic_tac_toe_win_checker.kotlin

import kotlin.test.assertEquals
import org.junit.Test

class TestExample {
    @Test
    fun someBoardsWithWinnersTests () {
        val winners = arrayOf("XXX-O-O-O", "X--OOOX-X", "O--OO-XXX", "O-XOX-O-X", "OXOOXOXX-", "X-O-OOXXO", "XO--X-OOX", "X-OXOOOXX")
        winners.forEach { winner ->
            println("Testing with board " + winner)
            assertEquals(true, winChecker(winner))
        }
    }

    @Test
    fun someBoardsWithoutWinnersTests () {
        val notWinners = arrayOf("XO-------", "XX-XOO---", "-XX-OO-O-", "OXO--XXO-", "OOXXXO---", "OXXX-XOO-", "OOXXX----", "XXOOXXOO-", "OXOXOX---")
        notWinners.forEach { notWinner ->
            println("Testing with board " + notWinner)
            assertEquals(false, winChecker(notWinner))
        }
    }

}
