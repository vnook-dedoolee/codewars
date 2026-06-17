// http://codewars.com/kata/5ca3ae9bb7de3a0025c5c740/train/kotlin

package kyu_6.change_your_points_of_view.kotlin

object PointView {
    fun point(a: Int, b: Int): () -> Pair<Int, Int> {
        return { Pair(a, b) }
    }

    fun fst(pt: () -> Pair<Int, Int>): Int {
        return pt().first
    }

    fun snd(pt: () -> Pair<Int, Int>): Int {
        return pt().second
    }

    fun sqrDist(pt1: () -> Pair<Int, Int>, pt2: () -> Pair<Int, Int>): Int {
        val (x1, y1) = pt1()
        val (x2, y2) = pt2()
        val dx = x2 - x1
        val dy = y2 - y1
        return dx * dx + dy * dy
    }

    fun line(pt1: () -> Pair<Int, Int>, pt2: () -> Pair<Int, Int>): IntArray {
        val (x1, y1) = pt1()
        val (x2, y2) = pt2()
        val l = y1 - y2
        val m = x2 - x1
        val n = x1 * y2 - x2 * y1
        return intArrayOf(l, m, n)
    }
}
