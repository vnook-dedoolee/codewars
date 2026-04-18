package kyu_4.build_a_simple_spellchecker.kotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.Test

@TestMethodOrder(OrderAnnotation::class)
class SpellCheckerTest {

    @Test
    @Order(1)
    fun singleWordEditDistance1() {

        assertEquals(mapOf("thea" to listOf("tea", "the")),
            correctSpelling("thea", arrayOf("hear", "tea", "the", "there")))

        assertEquals(mapOf("ble" to listOf("able", "bale", "bile", "bled", "blew", "blue")),
            correctSpelling("ble", arrayOf("able", "abler", "bale", "bile", "blah", "blue", "bled", "blew", "cable")))

        assertEquals(mapOf("cate" to listOf("bate", "care", "cats", "cite", "mate", "rate")),
            correctSpelling("cate", arrayOf("rate", "bate", "bite", "cite", "care", "cart", "cats", "mate", "skate", "caters")))

        assertEquals(mapOf("acr" to listOf("arc", "car")),
            correctSpelling("acr", arrayOf("acres", "car", "arc", "card")))

        assertEquals(mapOf("slat" to listOf("flat", "salt", "sat", "seat", "slant", "slap", "slate", "slit", "splat")),
            correctSpelling("slat", arrayOf("sat","tea","salt","seat","slap","slid","slit","late","flat","tear","salty","slate","slant","splat","state")))
    }

    @Test
    @Order(2)
    fun singleWordEditDistance2() {

        assertEquals(mapOf("packg" to listOf("package", "pkg")),
            correctSpelling("packg", arrayOf("kg", "pkg", "package", "packages")))

        assertEquals(mapOf("ra" to listOf("air","art","at","err","of","rank","read","t","tar","tray","way","wry")),
            correctSpelling("ra", arrayOf("t","at","of","air","art","end","err","tar","way","wry","dare","read","rank","tray","yarn")))

        assertEquals(mapOf("ra" to listOf("rat")), // edit distance 1 word added to previous example
            correctSpelling("ra", arrayOf("rat","t","at","of","air","art","end","err","way","wry","dare","read","rank","tray","yarn")))

        assertEquals(mapOf("slat" to listOf("at","flap","last","malt","sale","silt","slated","slid","slop","star","start")),
            correctSpelling("slat", arrayOf("a","at","ate","flap","flop","last","laser","malt","mal","sale","male","silt","silo","slid","slide","slop","sop","star","tsar","start","stale","slated","salted")))
    }

    @Test
    @Order(3)
    fun multipleWordsSimpleExamples() {

        assertEquals(linkedMapOf("speling" to listOf("spelling"), "corectins" to listOf("correcting","corrections"), "correctig" to listOf("correcting")),
            correctSpelling("my speling corectins need correctig", arrayOf("my","need","speaking","corrections","spelling","collection","correcting","spilling")))

        assertEquals(emptyMap<String, List<String>>(),
            correctSpelling("the quick brown fox", arrayOf("a","fox","dog","the","quick","black","brown")))

        assertEquals(linkedMapOf("wuggle" to emptyList(), "own" to listOf("brown")),
            correctSpelling("the quick own wuggle", arrayOf("a","fox","dog","the","quick","black","brown","burglar","bungler","womble")))

        assertEquals(linkedMapOf("thead" to listOf("the","thee","their","there"), "ther" to listOf("the","thee","their","there"), "hter" to listOf("the","thee","their","there")),
            correctSpelling("The thead is ther, not hter", arrayOf("is","not","the","thee","there","their")))
    }

    @Test
    @Order(4)
    fun multipleWordsHarderExamples() {

        assertEquals(linkedMapOf("hello-there" to emptyList(), "th;e*re" to listOf("there")),
            correctSpelling("hello-there hello -there ;*th;e*re,*?!", arrayOf("hello","there")))

        assertEquals(linkedMapOf("Ther" to listOf("The","Their"), "Quickk" to listOf("Quick"), "wont" to listOf("won't"), "droog" to listOf("dog","door")),
            correctSpelling("Ther Quickk BROWN fox wont' jump over the lazy droog.", arrayOf("the","fox","dog","jump","over","lazy","door","won't","quick","brown","doggy","their","wouldn't","quickly")))
    }

    @Test
    @Order(5)
    fun edgeCases() {

        assertEquals(emptyMap<String, List<String>>(), correctSpelling("", emptyArray()))
        assertEquals(emptyMap<String, List<String>>(), correctSpelling("", arrayOf("the","a")))
        assertEquals(emptyMap<String, List<String>>(), correctSpelling("   \n.  ", arrayOf("the","a")))

        assertEquals(linkedMapOf("a" to emptyList<String>(),"z;@+=z" to emptyList<String>()),
            correctSpelling("a , 'z;@+=z@", emptyArray()))

        assertEquals(mapOf("m" to listOf("a","ma","n","z")), correctSpelling("m", arrayOf("mad","ma","nb","z","a","n")))

        assertEquals(emptyMap<String, List<String>>(), correctSpelling("-", arrayOf("a","ba","c-z")))
    }
}
