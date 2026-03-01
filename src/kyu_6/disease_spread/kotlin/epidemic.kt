// https://www.codewars.com/kata/566543703c72200f0b0000c9/train/kotlin

package kyu_6.disease_spread.kotlin

fun epidemic(tm: Int, n: Int, s0: Int, i0: Int, b: Double, a: Double): Int {

    val dt = tm.toDouble() / n

    var s = s0.toDouble()
    var i = i0.toDouble()
    var r = 0.0

    var maxInfected = i

    for (step in 0 until n) {

        val newS = s - dt * b * s * i
        val newI = i + dt * (b * s * i - a * i)
        val newR = r + dt * a * i

        s = newS
        i = newI
        r = newR

        if (i > maxInfected) {
            maxInfected = i
        }
    }

    return maxInfected.toInt()
}
