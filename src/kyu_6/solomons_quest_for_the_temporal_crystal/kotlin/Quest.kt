// https://www.codewars.com/kata/59d7c910f703c460a2000034/train/kotlin

package kyu_6.solomons_quest_for_the_temporal_crystal.kotlin

object Quest {
    fun solomonsQuest(arr: Array<Triple<Int, Int, Int>>): Pair<Int, Int> {
        var x = 0
        var y = 0
        var layer = 0

        for ((shift, dir, dist) in arr) {
            layer += shift
            val standardDist = dist * (1 shl layer)

            when (dir) {
                0 -> y += standardDist
                1 -> x += standardDist
                2 -> y -= standardDist
                3 -> x -= standardDist
            }
        }

        return x to y
    }
}