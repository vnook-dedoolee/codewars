// https://www.codewars.com/kata/56445c4755d0e45b8c00010a/train/kotlin

package kyu_6.bankers_plan.kotlin

fun fortune(f0: Int, p: Double, c0: Int, n: Int, i: Double): Boolean {
    var F = f0.toDouble()
    var C = c0.toDouble()

    for (year in 0 until n - 1) {
        F += F * (p / 100.0) - C
        F = F.toInt().toDouble()
        C += C * (i / 100.0)
        C = C.toInt().toDouble()
        if (F < 0) return false
    }
    return true
}

