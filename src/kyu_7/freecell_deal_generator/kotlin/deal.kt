// https://www.codewars.com/kata/64eca9a7bc3127082b0bc7dc/train/kotlin

package kyu_7.freecell_deal_generator.kotlin

fun deal(dealNumber: Int): List<String> {
    val DECK = listOf(
        "AC", "AD", "AH", "AS", "2C", "2D", "2H", "2S", "3C", "3D", "3H", "3S", "4C",
        "4D", "4H", "4S", "5C", "5D", "5H", "5S", "6C", "6D", "6H", "6S", "7C", "7D",
        "7H", "7S", "8C", "8D", "8H", "8S", "9C", "9D", "9H", "9S", "TC", "TD", "TH",
        "TS", "JC", "JD", "JH", "JS", "QC", "QD", "QH", "QS", "KC", "KD", "KH", "KS"
    )

    var state = dealNumber
    val deck = DECK.toMutableList()
    val result = mutableListOf<String>()

    while (deck.isNotEmpty()) {
        state = ((state * 214013L + 2531011L) % (1L shl 31)).toInt()
        val nextValue = state ushr 16
        val index = nextValue % deck.size

        result.add(deck[index])

        if (index != deck.lastIndex) {
            deck[index] = deck.last()
        }
        deck.removeAt(deck.lastIndex)
    }

    return result
}