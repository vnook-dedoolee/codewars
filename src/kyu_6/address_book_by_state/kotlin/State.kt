// https://www.codewars.com/kata/59d0ee709f0cbcf65400003b/train/kotlin

package kyu_6.address_book_by_state.kotlin

object State {
    private val statesMap = mapOf(
        "AZ" to "Arizona",
        "CA" to "California",
        "ID" to "Idaho",
        "IN" to "Indiana",
        "MA" to "Massachusetts",
        "OK" to "Oklahoma",
        "PA" to "Pennsylvania",
        "VA" to "Virginia"
    )
    fun byState(str: String): String {
        val statesGroup = str.split("\n").groupBy(
            { statesMap[it.takeLast(2)] },
            { it }
        )
        return statesGroup.map { it ->
            "${it.key}" + it.value.sorted().joinToString(
                "\n..... ",
                "\n..... "
            ) { "${it.split(',').joinToString("").dropLast(2)}${statesMap[it.takeLast(2)]}" }
        }.sorted().joinToString("\n ")
    }
}



