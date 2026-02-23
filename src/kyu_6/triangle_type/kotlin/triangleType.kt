// https://www.codewars.com/kata/53907ac3cd51b69f790006c5/train/kotlin

package kyu_6.triangle_type.kotlin

import kotlin.math.pow
import kotlin.math.abs

/* Should return ᐃ type:
    0 : if ᐃ cannot be made with given sides
    1 : acute ᐃ
    2 : right ᐃ
    3 : obtuse ᐃ
*/
fun triangleType(a: Double, b: Double, c: Double): Int {
    if (!isValidTriangle(a, b, c)) return 0

    val cosAlpha = (b.pow(2) + c.pow(2) - a.pow(2)) / (2 * b * c)
    val cosBeta = (a.pow(2) + c.pow(2) - b.pow(2)) / (2 * a * c)
    val cosGamma = (a.pow(2) + b.pow(2) - c.pow(2)) / (2 * a * b)

    if (listOf(cosAlpha, cosBeta, cosGamma).any { it !in -1.0..1.0 }) return 0

    return when {
        isRightAngle(cosAlpha) || isRightAngle(cosBeta) || isRightAngle(cosGamma) -> 2
        isObtuseAngle(cosAlpha) || isObtuseAngle(cosBeta) || isObtuseAngle(cosGamma) -> 3
        else -> 1
    }
}

private fun isValidTriangle(a: Double, b: Double, c: Double): Boolean {
    return a > 0 && b > 0 && c > 0 &&
            a + b > c && a + c > b && b + c > a
}

private fun isRightAngle(cosValue: Double): Boolean {
    return abs(cosValue) < 1e-10
}

private fun isObtuseAngle(cosValue: Double): Boolean {
    return cosValue < 0
}
