package kyu_5.city_swim_2d_towerflood_and_plainflood.kotlin

import org.junit.Test
import kotlin.test.assertEquals

class ExampleTests {
	@Test fun `Basic test cases for rainVolume function`() {
		val simpleTests = arrayOf(
			Pair(0,intArrayOf()),
			Pair(3,intArrayOf(5,2,10)),
			Pair(7,intArrayOf(1,0,5,2,6,3,10)),
			Pair(20,intArrayOf(15,0,6,10,11,2,5)),
			Pair(0,intArrayOf(1,5,1)),
			Pair(0,intArrayOf(6,5))
		)
		
		for ((sol,towers) in simpleTests) assertEquals(sol,Kata.rainVolume(towers),"Should return $sol for towers=[ ${towers.joinToString(", ")} ]")
	}
}
