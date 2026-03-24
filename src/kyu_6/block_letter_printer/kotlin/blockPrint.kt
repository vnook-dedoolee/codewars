// https://www.codewars.com/kata/6375587af84854823ccd0e90/train/kotlin

package kyu_6.block_letter_printer.kotlin

val alpha = mapOf(
    'a' to listOf(
        " AAA ",
        "A   A",
        "A   A",
        "AAAAA",
        "A   A",
        "A   A",
        "A   A"
    ),
    'b' to listOf(
        "BBBB ",
        "B   B",
        "B   B",
        "BBBB ",
        "B   B",
        "B   B",
        "BBBB "
    ),
    'c' to listOf(
        " CCC ",
        "C   C",
        "C    ",
        "C    ",
        "C    ",
        "C   C",
        " CCC "
    ),
    'd' to listOf(
        "DDDD ",
        "D   D",
        "D   D",
        "D   D",
        "D   D",
        "D   D",
        "DDDD "
    ),
    'e' to listOf(
        "EEEEE",
        "E    ",
        "E    ",
        "EEEEE",
        "E    ",
        "E    ",
        "EEEEE"
    ),
    'f' to listOf(
        "FFFFF",
        "F    ",
        "F    ",
        "FFFFF",
        "F    ",
        "F    ",
        "F    "
    ),
    'g' to listOf(
        " GGG ",
        "G   G",
        "G    ",
        "G GGG",
        "G   G",
        "G   G",
        " GGG "
    ),
    'h' to listOf(
        "H   H",
        "H   H",
        "H   H",
        "HHHHH",
        "H   H",
        "H   H",
        "H   H"
    ),
    'i' to listOf(
        "IIIII",
        "  I  ",
        "  I  ",
        "  I  ",
        "  I  ",
        "  I  ",
        "IIIII"
    ),
    'j' to listOf(
        "JJJJJ",
        "  J  ",
        "  J  ",
        "  J  ",
        "  J  ",
        "J J  ",
        " JJ  "
    ),
    'k' to listOf(
        "K   K",
        "K  K ",
        "K K  ",
        "KK   ",
        "K K  ",
        "K  K ",
        "K   K"
    ),
    'l' to listOf(
        "L    ",
        "L    ",
        "L    ",
        "L    ",
        "L    ",
        "L    ",
        "LLLLL"
    ),
    'm' to listOf(
        "M   M",
        "MM MM",
        "M M M",
        "M   M",
        "M   M",
        "M   M",
        "M   M"
    ),
    'n' to listOf(
        "N   N",
        "NN  N",
        "N N N",
        "N  NN",
        "N   N",
        "N   N",
        "N   N"
    ),
    'o' to listOf(
        " OOO ",
        "O   O",
        "O   O",
        "O   O",
        "O   O",
        "O   O",
        " OOO "
    ),
    'p' to listOf(
        "PPPP ",
        "P   P",
        "P   P",
        "PPPP ",
        "P    ",
        "P    ",
        "P    "
    ),
    'q' to listOf(
        " QQQ ",
        "Q   Q",
        "Q   Q",
        "Q Q Q",
        "Q  QQ",
        "Q   Q",
        " QQQQ"
    ),
    'r' to listOf(
        "RRRR ",
        "R   R",
        "R   R",
        "RRRR ",
        "R R  ",
        "R  R ",
        "R   R"
    ),
    's' to listOf(
        " SSS ",
        "S   S",
        "S    ",
        " SSS ",
        "    S",
        "S   S",
        " SSS "
    ),
    't' to listOf(
        "TTTTT",
        "  T  ",
        "  T  ",
        "  T  ",
        "  T  ",
        "  T  ",
        "  T  "
    ),
    'u' to listOf(
        "U   U",
        "U   U",
        "U   U",
        "U   U",
        "U   U",
        "U   U",
        " UUU "
    ),
    'v' to listOf(
        "V   V",
        "V   V",
        "V   V",
        "V   V",
        "V   V",
        " V V ",
        "  V  "
    ),
    'w' to listOf(
        "W   W",
        "W   W",
        "W   W",
        "W W W",
        "W W W",
        "W W W",
        " W W "
    ),
    'x' to listOf(
        "X   X",
        "X   X",
        " X X ",
        "  X  ",
        " X X ",
        "X   X",
        "X   X"
    ),
    'y' to listOf(
        "Y   Y",
        "Y   Y",
        " Y Y ",
        "  Y  ",
        "  Y  ",
        "  Y  ",
        "  Y  "
    ),
    'z' to listOf(
        "ZZZZZ",
        "   Z ",
        "  Z  ",
        " Z   ",
        "Z    ",
        " Z   ",
        "ZZZZZ"
    ),
    ' ' to listOf(
        "     ",
        "     ",
        "     ",
        "     ",
        "     ",
        "     ",
        "     "
    )
)


fun blockPrint(input: String): String {
    val trimmedInput = input.trim()
    if (trimmedInput.isEmpty()) return ""

    val charRows = List(7) { StringBuilder() }

    for ((index, char) in trimmedInput.withIndex()) {
        val lowerChar = char.lowercaseChar()
        val charPattern = alpha[lowerChar] ?: return ""

        for (row in 0..6) {
            charRows[row].append(charPattern[row])
            if (index < trimmedInput.length - 1 || char != ' ') {
                charRows[row].append(' ')
            }
        }
    }

    return charRows.joinToString("\n") { it.toString().trimEnd() }
}
