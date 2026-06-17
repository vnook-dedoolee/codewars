// https://www.codewars.com/kata/5bce125d3bb2adff0d000245/train/kotlin

package kyu_7.london_city_hacker

import java.util.*

fun londonCityHacker(journey: Array<Any>): String {
    if (journey.isEmpty()) return "£0.00"
    var amount = 0.00
    var busCount = 0

    for (element in journey) {
        if (element is String) {
            if (busCount > 0) {
                amount += (busCount / 2 + busCount % 2) * 1.50
                busCount = 0
            }
            amount += 2.40
        } else {
            busCount++
        }
    }

    if (busCount > 0) {
        amount += (busCount / 2 + busCount % 2) * 1.50
    }

    val result = String.format(Locale.UK, "%.2f", amount)

    return "£$result"
}