// https://www.codewars.com/kata/5bc001de6aab18fba5000066/train/kotlin

package kyu_6.simple_fun_393_lonely_frog_V.kotlin

fun jumping(arr: IntArray): Boolean {
    var maxReach = 0
    for (i in arr.indices) {
        if (i > maxReach) return false
        maxReach = maxOf(maxReach, i + arr[i])
        if (maxReach >= arr.lastIndex) return true
    }
    return true
}