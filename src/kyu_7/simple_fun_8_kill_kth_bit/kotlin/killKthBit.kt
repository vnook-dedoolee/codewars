// https://www.codewars.com/kata/58844f1a76933b1cd0000023/train/kotlin

package kyu_7.simple_fun_8_kill_kth_bit.kotlin

fun killKthBit(n: Int, k: Int): Int {
    if (n < 1 || k < 1) return n
    val mask = 1 shl (k - 1)
    return n and (mask.inv())
}
