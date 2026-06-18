//package kyu_6.change_your_points_of_view.kotlin
//
//import org.junit.Test
//import kotlin.test.assertEquals
//
//class PointViewTest {
//    fun testing0(a: Int, b: Int) {
//        val methods = PointView::class.java.methods
//        var actual = false
//        for (method in methods) {
//            if (method.name.equals("point")) {
//                val returnType = method.returnType
//                if (returnType.name.substring(0, 29) == "kotlin.jvm.functions.Function")
//                    actual = true
//            }
//        }
//        assertEquals(true, actual)
//        val actual1 = PointView.fst(PointView.point(a, b))
//        assertEquals(a, actual1)
//        val actual2 = PointView.snd(PointView.point(a, b))
//        assertEquals(b, actual2)
//    }
//    fun testing2(a1: Int, b1: Int, a2: Int, b2: Int, expect: Int) {
//        val actual = PointView.sqrDist(PointView.point(a1, b1), PointView.point(a2, b2))
//        assertEquals(expect, actual)
//    }
//    fun testing3(a1: Int, b1: Int, a2: Int, b2: Int, expect: IntArray) {
//        val actual = PointView.line(PointView.point(a1, b1), PointView.point(a2, b2))
//        val check = Helper.lineEqual(actual, expect)
//        assertEquals(true, check)
//    }
//    @Test
//    fun test0() {
//        println("Basic Tests Function Point, fst, snd");
//        testing0(3, 4);
//        testing0(24, 37);
//        testing0(17, 98);
//
//    }
//    @Test
//    fun test1() {
//        println("Basic Tests sqrDist");
//        testing2(22, 55, 75, 66, 2930);
//        testing2(11, 22, 65, 44, 3400);
//
//    }
//    @Test
//    fun test2() {
//        println("Basic Tests line")
//        testing3(20, 22, 29, 10, intArrayOf(4, 3, -146))
//        testing3(38, 32, 14, 8, intArrayOf(24, -24, -144))
//
//    }
//}
