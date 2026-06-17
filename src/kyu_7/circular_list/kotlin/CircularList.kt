// https://www.codewars.com/kata/5b2e60742ae7543f9d00005d/train/kotlin

package kyu_7.circular_list.kotlin

class CircularList<T>(vararg val elements: T) {

    private val list = elements.toList()
    private var idx = -1

    init {
        if (elements.isEmpty()) throw NoSuchElementException()
    }

    fun next(): T {
        idx = (idx + 1) % list.size
        return list[idx]
    }

    fun prev(): T {
        idx = if (idx == -1) {
            list.size - 1
        } else {
            (idx - 1 + list.size) % list.size
        }
        return list[idx]
    }
}
