// https://www.codewars.com/kata/581ee0db1bbdd04e010002fd/train/kotlin

package kyu_6.floating_point_approximation_ll.kotlin

import kotlin.math.floor

fun interp(f: (Double) -> Double, l: Double, u: Double, n: Int): List<Double> {
    val d = (u - l) / n
    return (0 until n).map { i ->
        val x = l + i * d
        /*
        + 1e-9 (очень маленькое число) компенсирует погрешность вычислений с плавающей точкой, чтобы 7.199999...
        стало 7.200000... и округлилось правильно
         */
        floor(f(x) * 100.0 + 1e-9) / 100.0
    }
}
