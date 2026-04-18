package kyu_7.easy_wallpaper.kotlin

import org.junit.Test
import org.junit.Assert.*

class EasyWallpaperTest {
    @Test
    fun basicTests() {
        assertEquals("ten", wallpaper(4.0, 3.5, 3.0))
        assertEquals("sixteen", wallpaper(6.3, 4.5, 3.29))
        assertEquals("zero", wallpaper(8.49, 9.16, 0.0))
    }
}