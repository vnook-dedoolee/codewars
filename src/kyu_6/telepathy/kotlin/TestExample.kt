package kyu_6.telepathy.kotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Test Example")
class TestExample {
    @DisplayName("Basic Tests")
    @Test
    fun basicTests() {
        assertEquals(47, magicShow("| Card 1: Yes | Card 2: Yes | Card 3: Yes | Card 4: Yes | Card 5: No | Card 6: Yes |"))
        assertEquals(46, magicShow("| Card 1: No | Card 2: Yes | Card 3: Yes | Card 4: Yes | Card 5: No | Card 6: Yes |"))
        assertEquals(55, magicShow("| Card 1: Yes | Card 2: Yes | Card 3: Yes | Card 4: No | Card 5: Yes | Card 6: Yes |"))
        assertEquals(38, magicShow("| Card 1: No | Card 2: Yes | Card 3: Yes | Card 4: No | Card 5: No | Card 6: Yes |"))
    }
}
