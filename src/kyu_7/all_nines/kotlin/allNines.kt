// https://www.codewars.com/kata/664b9dd610985cc3b6784111/train/kotlin

package kyu_7.all_nines.kotlin

import java.math.BigInteger

fun allNines(x: BigInteger): BigInteger {
    if (x.mod(BigInteger.valueOf(2)) == BigInteger.ZERO ||
        x.mod(BigInteger.valueOf(5)) == BigInteger.ZERO) {
        return BigInteger.valueOf(-1)
    }
    var n = BigInteger.ONE
    var num = BigInteger.valueOf(9)

    while (true) {
        if (num.mod(x) == BigInteger.ZERO) {
            return num.divide(x)
        }
        n = n.add(BigInteger.ONE)
        num = num.multiply(BigInteger.TEN).add(BigInteger.valueOf(9))
    }
}

