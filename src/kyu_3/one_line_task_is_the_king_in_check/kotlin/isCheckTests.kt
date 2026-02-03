package kyu_3.one_line_task_is_the_king_in_check.kotlin

import org.junit.Test
import kotlin.test.assertEquals

class isCheckTests {

    @Test
    fun shouldWorkWithCheckByPawn() {
        val board = arrayOf(
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," ","♟"," "," "," "," "),
            arrayOf(" "," ","♔"," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "))
        assertEquals(isCheck(board), true)
    }

    @Test
    fun shouldWorkWithPawnDirectlyAbove() {
        val board = arrayOf(
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," ","♟"," "," "," "," "," "),
            arrayOf(" "," ","♔"," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "))
        assertEquals(isCheck(board), false)
    }

    @Test
    fun shouldWorkWithCheckByBishop() {
        val board = arrayOf(
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," ","♔"," "," "," "," "," "),
            arrayOf(" "," "," ","♝"," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "))
        assertEquals(isCheck(board), true)
    }

    @Test
    fun shouldWorkWithCheckByBishop2() {
        val board = arrayOf(
            arrayOf(" "," "," "," "," "," "," ","♝"),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf("♔"," "," "," "," "," "," "," "))
        assertEquals(isCheck(board), true)
    }

    @Test
    fun shouldWorkWithCheckByRook() {
        val board = arrayOf(
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" ","♔"," "," "," ","♜"," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "))
        assertEquals(isCheck(board), true)
    }

    @Test
    fun shouldWorkWithCheckByKnight() {
        val board = arrayOf(
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" ","♔"," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf("♞"," "," "," "," "," "," "," "))
        assertEquals(isCheck(board), true)
    }

    @Test
    fun shouldWorkWithCheckByQueen() {
        val board = arrayOf(
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" ","♔"," "," ","♛"," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "))
        assertEquals(isCheck(board), true)
    }

    @Test
    fun shouldWorkWithKingAlone() {
        val board = arrayOf(
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" ","♔"," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "))
        assertEquals(isCheck(board), false)
    }

    @Test
    fun shouldWorkWhenNoCheck() {
        val board = arrayOf(
            arrayOf("♛"," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," ","♞"," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," ","♛"),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" ","♔"," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "))
        assertEquals(isCheck(board), false)
    }

    @Test
    fun shouldWorkWhenAPieceIsBlockingLineOfSight() {
        val board = arrayOf(
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" ","♔"," ","♞","♛"," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "))
        assertEquals(isCheck(board), false)
    }

    @Test
    fun noCheck() {
        val board = arrayOf(
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf("♔"," "," "," "," "," "," ","♞"),
            arrayOf(" "," "," "," "," "," "," "," "),
            arrayOf(" "," "," "," "," "," "," "," "))
        assertEquals(false, isCheck(board))
    }
}