// https://www.codewars.com/kata/57cebe1dc6fdc20c57000ac9/train/kotlin

package kyu_7.shortest_word.kotlin

fun findShort(s: String): Int {
    return s.split(" ").minOf { it.length }
}
