//package kyu_6.allocating_hotel_rooms.kotlin
//
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.Assertions.assertTrue
//
//class RoomAllocationTest {
//
//    @Test
//    fun exampleTests() {
//        val testCases = arrayOf(
//            arrayOf(intArrayOf(1, 2),   intArrayOf(2, 4),   intArrayOf(4, 4)),
//            // Solution [1,2,1] or [2,1,2]
//
//            arrayOf(intArrayOf(1, 5),   intArrayOf(2, 4),   intArrayOf(6, 8),  intArrayOf(7, 7)),
//            // Solution [1,2,1,2], [1,2,2,1], [2,1,2,1], or [2,1,1,2]
//
//            arrayOf(intArrayOf(15, 22), intArrayOf(2, 4),   intArrayOf(6, 9),  intArrayOf(3, 33), intArrayOf(12, 21)),
//            // Solution [1,2,2,3,2], [2,1,1,3,1], [3,1,3,2,1], etc
//
//            arrayOf(intArrayOf(1, 10),  intArrayOf(2, 5),   intArrayOf(6, 6),  intArrayOf(3, 7),
//                intArrayOf(6, 6),   intArrayOf(11, 13), intArrayOf(9, 15), intArrayOf(8, 14)),
//            // Solutions include [1,2,2,3,4,1,3,2], [1,2,2,3,4,1,2,3],
//            //                   [1,2,4,3,2,1,3,2], [2,3,3,1,4,2,1,3], and others
//
//            arrayOf(intArrayOf(8, 8),   intArrayOf(5, 8),   intArrayOf(8, 9),  intArrayOf(1, 4),
//                intArrayOf(1, 3),   intArrayOf(5, 7),   intArrayOf(4, 8),  intArrayOf(2, 2),
//                intArrayOf(4, 5),   intArrayOf(6, 8))
//            // Solutions include [4, 1, 5, 1, 2, 4, 2, 3, 3, 3], [5, 4, 2, 2, 1, 2, 3, 3, 1, 1], and others
//        )
//
//        val roomsNeeded = intArrayOf(2, 2, 3, 4, 5)
//
//        for (i in testCases.indices) {
//            val thisTest = testCases[i]
//            val actualValue = allocateRooms(thisTest.map { it.clone() }.toTypedArray())
//            val result = validateSolution(thisTest, actualValue, roomsNeeded[i])
//            assertTrue(result.first, result.second)
//        }
//    }
//
//    @Test
//    fun specialCases() {
//
//        // One customer - solution is [1]
//        val specialCase1 = arrayOf(intArrayOf(5, 100))
//        val actual1 = allocateRooms(specialCase1.map { it.clone() }.toTypedArray())
//        val result1 = validateSolution(specialCase1, actual1, 1)
//        assertTrue(result1.first, result1.second)
//
//        // Non-overlapping customers, only 1 room needed. Solution is [1,1,1,1,1,1,1]
//        val specialCase2 = arrayOf(intArrayOf(15, 19), intArrayOf(1, 2), intArrayOf(3, 5),
//            intArrayOf(10, 10), intArrayOf(6, 9), intArrayOf(20, 99),
//            intArrayOf(101, 101))
//        val actual2 = allocateRooms(specialCase2.map { it.clone() }.toTypedArray())
//        val result2 = validateSolution(specialCase2, actual2, 1)
//        assertTrue(result2.first, result2.second)
//
//        // All customers overlap, so each needs a new room. Solution is any permutation of [1,2,3,4,5]
//        val specialCase3 = arrayOf(intArrayOf(4, 7), intArrayOf(1, 10), intArrayOf(2, 5),
//            intArrayOf(3, 4), intArrayOf(3, 12))
//        val actual3 = allocateRooms(specialCase3.map { it.clone() }.toTypedArray())
//        val result3 = validateSolution(specialCase3, actual3, 5)
//        assertTrue(result3.first, result3.second)
//    }
//
//}
//
//
