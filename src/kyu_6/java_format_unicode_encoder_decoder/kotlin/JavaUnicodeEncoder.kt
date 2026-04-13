// https://www.codewars.com/kata/58e2c062542a419083000033/train/kotlin

package kyu_6.java_format_unicode_encoder_decoder.kotlin

object JavaUnicodeEncoder {
    fun encode(str: String): String {
        return str.map { ch ->
            "\\u%04x".format(ch.code)
        }.joinToString("")
    }

    fun decode(str: String): String {
        val pattern = Regex("\\\\u([0-9a-f]{4})")
        return pattern.replace(str) { matchResult ->
            val hex = matchResult.groupValues[1]
            val code = hex.toInt(16)
            code.toChar().toString()
        }
    }
}