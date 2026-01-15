package kyu_6.throw_without_throwing.kotlin

import java.nio.CharBuffer
import java.util.*

fun arrayIndexOutOfBound() {
    val a = arrayOf(2)
    val b = a[2]
}

fun negativeArraySize() {
    val x = IntArray(-1)
}

fun noSuchElement() {
    val h = setOf<Int>()
    val x = h.iterator().next()
}

fun arithmetic() {
    val a = 1 / 0
}

fun classCast() {
    val s = setOf<Int>()
    val t = s as Map<Int, Int>
}

fun stackOverflow() {
    stackOverflow()
}

fun nullPointer() {
    val a = null
    val b = a!!.toInt()
}

fun numberFormat() {
    val a = "hello".toInt()
}

fun illegalArgument() {
    Thread.currentThread().priority = -1
}

fun emptyStack() {
    val b = Stack<Int>()
    b.pop()
}

fun bufferOverflow() {
    val ch = CharBuffer.allocate(1)
    ch.put('a')
    ch.put('b')
}

fun arrayStore() {
    val a = ubyteArrayOf(1u, 2u)
    val b = UByteArray(2)
    System.arraycopy(a, 0, b, 0, 2)
}

fun unsupportedOperation() {
    val a = mapOf(1 to 1)
    val b = a as MutableMap<Int, Int>
    b.put(2, 3)
}

fun illegalState() {
    val list = mutableListOf(1, 2, 3)
    val iterator = list.iterator()
    iterator.next()
    iterator.remove()
    iterator.remove()
}