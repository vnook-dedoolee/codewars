package kyu_8.returning_strings.kotlin

import kotlin.test.assertEquals
import org.junit.Test

class SampleTests {
  @Test
  fun `Basic tests`() {
    assertEquals("Hello, Ryan how are you doing today?", greet("Ryan"))
    assertEquals("Hello, Shingles how are you doing today?", greet("Shingles"))
  }
}
