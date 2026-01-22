// https://www.codewars.com/kata/59eb80eea95427d81a0000fc/train/kotlin

package kyu_6.tricky_kotlin_4_prevent_stackoverflow.kotlin

import java.util.*

tailrec fun loop(random: Random, int: Int): Int {
    if (int <= 0) return random.nextInt()
    random.nextInt()
    return loop(random, int - 1)

}

