// https://www.codewars.com/kata/64ad571aa33413003e712168/train/kotlin

package kyu_6.telepathy.kotlin

fun magicShow(ans: String): Int = ans.split(": Yes").dropLast(1).sumOf { (1 shl (it.substringAfterLast("Card ")[0].digitToInt() - 1)) }
