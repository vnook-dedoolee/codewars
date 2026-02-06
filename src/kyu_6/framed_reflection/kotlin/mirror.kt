// https://www.codewars.com/kata/581331293788bc1702001fa6/train/kotlin

package kyu_6.framed_reflection.kotlin

fun mirror(text: String): String {
    var result = ""
    val words = text.split(" ")
    val maxLength = text.split(" ").maxOf { it.length }
    result += "*".repeat(maxLength + 4) + "\n"
    for (word in words) {
        result += "* " + word.reversed() + " ".repeat(maxLength - word.length) + " *\n"
    }
    result += "*".repeat(maxLength + 4)
    return result
}