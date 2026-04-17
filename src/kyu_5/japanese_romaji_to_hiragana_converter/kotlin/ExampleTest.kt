package kyu_5.japanese_romaji_to_hiragana_converter.kotlin

import kotlin.test.assertEquals
import org.junit.Test
class ExampleTest {
    @Test
    fun exampleTests() {
        assertEquals("かた", romajiToHiragana("kata")) // form (of practice)
        assertEquals("いえ", romajiToHiragana("ie")) // house
        assertEquals("おまつり", romajiToHiragana("omatsuri")) // festival
        assertEquals("ちはやふる", romajiToHiragana("chihayafuru")) // a popular manga
        assertEquals("さっか", romajiToHiragana("sakka")) // writer
        assertEquals("がんばって", romajiToHiragana("ganbatte")) // good luck
        assertEquals("びょういん", romajiToHiragana("byouin")) // hispital
        assertEquals("こんにちは", romajiToHiragana("konnichiha")) // hello
    }
}