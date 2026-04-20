package kyu_6.computing_the_complex_logarithm_function.kotlin

import org.junit.Test

import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import kotlin.math.abs

open class LogTest {

    @Test
    fun ExampleTests() {
        AssertComplexEquals(doubleArrayOf(2.995732273553991, 0.0), log(doubleArrayOf(20.0, 0.0)))
        AssertComplexEquals(doubleArrayOf(0.0, 3.141592653589793), log(doubleArrayOf(-1.0, 0.0)))
        AssertComplexEquals(doubleArrayOf(0.346573590279973, 0.785398163397448), log(doubleArrayOf(1.0, 1.0)))
        assertThrows(ArithmeticException::class.java) { log(doubleArrayOf(0.0, 0.0)) }
    }

    @Test
    fun FixedTests() {
        AssertComplexEquals(doubleArrayOf(3.218875824868201, 2.857798544381465), log(doubleArrayOf(-24.0, 7.0)))
        AssertComplexEquals(doubleArrayOf(0.0, 1.570796326794897), log(doubleArrayOf(0.0, 1.0)))
        AssertComplexEquals(doubleArrayOf(0.0, 0.0), log(doubleArrayOf(1.0, 0.0)))
        AssertComplexEquals(doubleArrayOf(1.598433436873693, 0.463447163733142), log(doubleArrayOf(8834.0 / 1997.0, 93387.0 / 42243.0)))
        AssertComplexEquals(doubleArrayOf(11.275831160977204, 1.203262110987354), log(doubleArrayOf(28347.0, 73623.0)))
        // Let's test this edge case one more time ;)
        assertThrows(ArithmeticException::class.java) { log(doubleArrayOf(0.0, 0.0)) }
    }
    companion object {
        protected fun AssertComplexEquals(expected: DoubleArray, actual: DoubleArray) {
            if (abs(expected[0]) <= 1)
                assertEquals("The real part of your returned complex number is not sufficiently close to the expected value",
                    actual[0], expected[0], 1e-12)
            else
                assertEquals("The real part of your returned complex number is not sufficiently close to the expected value",
                    actual[0], expected[0], 1e-10)
            if (abs(expected[1]) <= 1)
                assertEquals("The imaginary part of your returned complex number is not sufficiently close to the expected value",
                    actual[1], expected[1], 1e-12)
            else
                assertEquals("The imaginary part of your returned complex number is not sufficiently close to the expected value",
                    actual[1], expected[1], 1e-10)
        }

        private fun assertThrows(clazz: Class<out Throwable>, function: () -> Unit) {
            try {
                function()
            } catch (e: Throwable) {
                if (e.javaClass == clazz)
                    println("Successfully thrown " + e.javaClass.simpleName
                            + ", message: " + e.message)
                else
                    fail("Error: expected: " + clazz.simpleName + ", get: " + e.javaClass.simpleName)
                return
            }

            fail("No exceptions thrown!")
        }

    }
}