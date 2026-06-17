// https://www.codewars.com/kata/63b84f54693cb10065687ae5/train/kotlin

package kyu_7.the_spiraling_box

fun createBox(width: Int, length: Int): Array<IntArray> {
    return Array(length) { i ->
        IntArray(width) { j ->
            val iDistance = minOf(i, length - 1 - i) + 1
            val jDistance = minOf(j, width - 1 - j) + 1
            minOf(iDistance, jDistance)
        }
    }
}