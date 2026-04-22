package kyu_5.complete_binary_tree.kotlin

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random

class TestCompleteBinaryTree {

    @Test
    fun `test single node tree`() {
        val input = intArrayOf(1)
        val expected = intArrayOf(1)
        assertArrayEquals(expected, completeBinaryTree(input))
    }

    @Test
    fun `test tree with two nodes`() {
        val input = intArrayOf(1, 2)
        val expected = intArrayOf(2, 1)
        assertArrayEquals(expected, completeBinaryTree(input))
    }

    @Test
    fun `test tree with six nodes`() {
        val input = intArrayOf(1, 2, 3, 4, 5, 6)
        val expected = intArrayOf(4, 2, 6, 1, 3, 5)
        assertArrayEquals(expected, completeBinaryTree(input))
    }

    @Test
    fun `test tree with ten nodes`() {
        val input = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expected = intArrayOf(7, 4, 9, 2, 6, 8, 10, 1, 3, 5)
        assertArrayEquals(expected, completeBinaryTree(input))
    }
}