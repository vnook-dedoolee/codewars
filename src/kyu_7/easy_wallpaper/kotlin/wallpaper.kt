// https://www.codewars.com/kata/567501aec64b81e252000003/train/kotlin

package kyu_7.easy_wallpaper.kotlin

import kotlin.math.ceil

private val numbers = arrayListOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
    "eleven", "twelve","thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty")
private const val WALLPAPER_SIZE = 0.52 * 10.0

fun wallpaper(l: Double, w: Double, h: Double): String {
    if (l == 0.0 || w == 0.0 || h == 0.0) return numbers[0]
    val perimeter = 2 * (l + w)
    val wallArea = perimeter * h
    val result = ceil((wallArea / WALLPAPER_SIZE) * 1.15).toInt()
    return if (result <= 20) numbers[result] else numbers[20]
}