package kyu_6.piano_kata_part_1.kotlin

fun blackOrWhiteKey(keyPressCount: Int): String {
    val key = ((keyPressCount - 1) % 88) + 1
    val pattern = listOf("white", "black", "white", "white", "black", "white", "black", "white", "white", "black", "white", "black")
    val patternIndex = (key - 1) % 12
    return pattern[patternIndex]
}