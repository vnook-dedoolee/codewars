// https://www.codewars.com/kata/5a1e6323ffe75f71ae000026/train/kotlin

package kyu_7.simple_fun_384_is_turings_equation.kotlin

fun isTuringEquation(s: String): Boolean {
    val (left, right) = s.split("=")
    val (a, b) = left.split("+")
    return a.reversed().toLong() + b.reversed().toLong() == right.reversed().toLong()
}
