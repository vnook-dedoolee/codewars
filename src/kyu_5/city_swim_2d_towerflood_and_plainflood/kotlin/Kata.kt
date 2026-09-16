// https://www.codewars.com/kata/58e77c88fd2d893a77000102/train/kotlin

package kyu_5.city_swim_2d_towerflood_and_plainflood.kotlin

object Kata {
    fun rainVolume(towers: IntArray): Int {
        if (towers.size < 3) return 0

        var left = 0
        var right = towers.size - 1
        var leftMax = 0
        var rightMax = 0
        var volume = 0

        while (left < right) {
            if (towers[left] < towers[right]) {
                if (towers[left] >= leftMax) {
                    leftMax = towers[left]
                } else {
                    volume += leftMax - towers[left]
                }
                left++
            } else {
                if (towers[right] >= rightMax) {
                    rightMax = towers[right]
                } else {
                    volume += rightMax - towers[right]
                }
                right--
            }
        }
        return volume
    }
}
