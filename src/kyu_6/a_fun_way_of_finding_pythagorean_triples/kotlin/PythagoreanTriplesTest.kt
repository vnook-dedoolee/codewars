package kyu_6.a_fun_way_of_finding_pythagorean_triples.kotlin

import org.junit.Test
import kotlin.test.assertEquals

class PythagoreanTriplesTest {

    @Test
    fun sampleTests() {
        assertEquals(listOf(listOf(3, 4, 5), listOf(5, 12, 13), listOf(7, 24, 25), listOf(9, 40, 41)), createPythagoreanTriples(diff = 1, low = 2, high = 10))

        assertEquals(
            listOf(listOf(10, 24, 26), listOf(12, 35, 37), listOf(14, 48, 50), listOf(16, 63, 65), listOf(18, 80, 82), listOf(20, 99, 101), listOf(22, 120, 122), listOf(24, 143, 145)),
            createPythagoreanTriples(diff = 2, low = 10, high = 25)
        )

        assertEquals(listOf(listOf(15, 36, 39), listOf(21, 72, 75)), createPythagoreanTriples(diff = 3, low = 10, high = 25))

        assertEquals(listOf(listOf(105, 608, 617), listOf(111, 680, 689), listOf(117, 756, 765)), createPythagoreanTriples(diff = 9, low = 100, high = 120)
        )

        assertEquals(listOf(listOf(330, 5440, 5450), listOf(340, 5775, 5785)), createPythagoreanTriples(diff = 10, low = 321, high = 345))

        assertEquals(emptyList<List<Int>>(), createPythagoreanTriples(diff = 100, low = 901, high = 919))

        assertEquals(listOf(listOf(7035, 9380, 11725)),createPythagoreanTriples(diff = 2345, low = 678, high = 9876))

        assertEquals(listOf(listOf(281120, 11243043, 11246557), listOf(284634, 11525920, 11529434), listOf(288148, 11812311, 11815825), listOf(291662, 12102216, 12105730), listOf(295176, 12395635, 12399149), listOf(298690, 12692568, 12696082), listOf(302204, 12993015, 12996529), listOf(305718, 13296976, 13300490), listOf(309232, 13604451, 13607965), listOf(312746, 13915440, 13918954), listOf(316260, 14229943, 14233457),
            listOf(319774, 14547960, 14551474), listOf(323288, 14869491, 14873005), listOf(326802, 15194536, 15198050), listOf(330316, 15523095, 15526609), listOf(333830, 15855168, 15858682), listOf(337344, 16190755, 16194269), listOf(340858, 16529856, 16533370), listOf(344372, 16872471, 16875985), listOf(347886, 17218600, 17222114), listOf(351400, 17568243, 17571757), listOf(354914, 17921400, 17924914),
            listOf(358428, 18278071, 18281585), listOf(361942, 18638256, 18641770), listOf(365456, 19001955, 19005469), listOf(368970, 19369168, 19372682), listOf(372484, 19739895, 19743409), listOf(375998, 20114136, 20117650), listOf(379512, 20491891, 20495405), listOf(383026, 20873160, 20876674), listOf(386540, 21257943, 21261457), listOf(390054, 21646240, 21649754), listOf(393568, 22038051, 22041565), listOf(397082, 22433376, 22436890), listOf(400596, 22832215, 22835729)),
            createPythagoreanTriples(diff = 3514, low = 279314, high = 401782))

        assertEquals(createPythagoreanTriples(diff = 8696, low = 945745, high = 2531281).size, 365)
    }

    @Test
    fun edgeCases() {
        assertEquals(emptyList<List<Int>>(), createPythagoreanTriples(11, 347, 347), "Should handle the case low = high, with no result")

        assertEquals(listOf(listOf(124, 957, 965)), createPythagoreanTriples(8, 124, 124), "Should handle the case low = high, one result")

        assertEquals(emptyList<List<Int>>(),createPythagoreanTriples(564, 456, 1654), "Should handle the case low < diff, no result")

        assertEquals(listOf(listOf(213, 284, 355)), createPythagoreanTriples(71, 10, 222), "Should handle the case low < diff, one result")

        assertEquals(listOf(listOf(45, 60, 75), listOf(75, 180, 195), listOf(105, 360, 375)),
            createPythagoreanTriples(15, 1, 123), "Should handle the case low < diff, many results")

        assertEquals(emptyList<List<Int>>(), createPythagoreanTriples(332, 1, 312), "Should handle the case where both low < diff and z < diff, no result")
    }
}
