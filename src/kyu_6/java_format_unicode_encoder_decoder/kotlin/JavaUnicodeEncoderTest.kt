package kyu_6.java_format_unicode_encoder_decoder.kotlin

import org.junit.Assert
import org.junit.Test

class JavaUnicodeEncoderTest {
    @Test
    @Throws(Exception::class)
    fun decode() {
        Assert.assertEquals("hola", JavaUnicodeEncoder.decode("\\u0068\\u006f\\u006c\\u0061"))
    }
    @Test
    @Throws(Exception::class)
    fun encode() {
        Assert.assertEquals("\\u0068\\u006f\\u006c\\u0061", JavaUnicodeEncoder.encode("hola"))
    }
}