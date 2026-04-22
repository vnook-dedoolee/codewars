// https://www.codewars.com/kata/5a90c9ecb171012b47000077/train/kotlin

package kyu_6.thinking_and_testing_ab.kotlin

fun testIt(a: Int, b: Int): Int {
    return (a.toString().sumOf { it.digitToInt() } * b.toString().sumOf { it.digitToInt() })
}