// https://www.codewars.com/kata/5886c6b2f3b6ae33dd0000be/train/kotlin

package kyu_7.simple_fun_20_first_reverse_try.kotlin

fun firstReverseTry(arr: IntArray) : IntArray {
    if (arr.isEmpty()) return arr
    val first = arr[0]
    val last = arr[arr.size - 1]
    arr[0] = last
    arr[arr.size - 1] = first
    return arr
}