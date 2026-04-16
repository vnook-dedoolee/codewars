// https://www.codewars.com/kata/569218bc919ccba77000000b/train/kotlin

package kyu_7.target_date.kotlin

import java.time.LocalDate

fun dateNbDays(a0: Double, a: Double, p: Double): String {
    val dailyRate = p / 36000
    var days = 0
    var current = a0
    var date = LocalDate.of(2016, 1, 1)

    while (current < a) {
        current += current * dailyRate
        days++
        date = date.plusDays(1)
    }

    return date.toString()
}