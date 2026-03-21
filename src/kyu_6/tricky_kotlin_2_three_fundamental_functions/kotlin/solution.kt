// https://www.codewars.com/kata/59b33fb95227ddcb5f0000d4/train/kotlin

package kyu_6.tricky_kotlin_2_three_fundamental_functions.kotlin

inline fun unless(condition: Boolean, block: () -> Unit) {
    if (!condition) {
        block()
    }
}

inline fun until(condition: () -> Boolean, body: () -> Unit) {
    while (!condition()) {
        body()
    }
}

inline fun forceRun(block: () -> Unit) {
    try {
        block()
    } catch (_: Throwable) {
    }
}
