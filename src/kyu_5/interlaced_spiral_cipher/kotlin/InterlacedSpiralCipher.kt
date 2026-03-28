// https://www.codewars.com/kata/5a24a35a837545ab04001614/train/kotlin

package kyu_5.interlaced_spiral_cipher.kotlin

import kotlin.math.ceil
import kotlin.math.sqrt

object InterlacedSpiralCipher {
    fun encode(s: String): String {
        val size = matrixSize(s.length)
        val builder = StringBuilder(" ".repeat(size * size))
        generateIndices(size).forEachIndexed { index, newIndex ->
            builder[newIndex] = s.getOrNull(index) ?: ' '
        }
        return builder.toString()
    }

    fun decode(s: String): String {
        return generateIndices(matrixSize(s.length))
            .map { s.getOrNull(it) ?: ' ' }
            .joinToString(separator = "")
            .trimEnd()
    }

    private fun generateIndices(size: Int): List<Int> {
        val indices = mutableListOf<Int>()
        val maxDimension = size - 1
        for (depth in 0..maxDimension / 2) {
            for (i in 0 until (maxDimension - depth * 2)) {
                indices += depth * size + depth + i
                indices += (depth + i) * size + maxDimension - depth
                indices += (maxDimension - depth) * size + maxDimension - depth - i
                indices += (maxDimension - depth - i) * size + depth
            }
        }
        if (size % 2 != 0) {
            indices += size * size / 2
        }
        return indices
    }

    private fun matrixSize(length: Int) = ceil(sqrt(length.toDouble())).toInt()
}

