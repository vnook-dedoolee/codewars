package kyu_6.zig_zag_camel.kotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Tests")
class TestZigZagCamel {
  @Test
  @DisplayName("test")
  fun test() {
    // assertEquals(expected, actual, delta)
    assertEquals(Math.hypot(10.0, 5.0), zigZagCamel(10.0, 5.0), 1e-9);
  }
}
