package kyu_6.solomons_quest_for_the_temporal_crystal.kotlin

import org.junit.Test
import kotlin.test.assertEquals

class ExampleTests {
    private fun runTest(sol:Pair<Int,Int>,r:Array<Triple<Int,Int,Int>>) = assertEquals(sol,Quest.solomonsQuest(r))

    @Test fun `Basic tests`() {
        runTest(
            346 to 40,
            arrayOf(Triple(1,3,5),Triple(2,0,10),Triple(-3,1,4),Triple(4,2,4),Triple(1,1,5),Triple(-3,0,12),Triple(2,1,12),Triple(-2,2,6)))
        runTest(
            68 to 800,
            arrayOf(Triple(4,0,8),Triple(2,1,2),Triple(1,0,5),Triple(-3,3,16),Triple(2,2,2),Triple(-1,1,7),Triple(0,0,5),Triple(-4,3,14)))
        runTest(
            -600 to -244,
            arrayOf(Triple(1,1,20),Triple(1,2,30),Triple(1,3,8),Triple(1,0,2),Triple(1,1,6),Triple(1,2,4),Triple(1,3,6),Triple(-7,0,100)))
        runTest(
            530 to 15664,
            arrayOf(Triple(2,2,100),Triple(3,1,25),Triple(4,0,8),Triple(-6,3,25),Triple(-1,2,80),Triple(8,0,12),Triple(-10,3,220),Triple(0,1,150)))
        runTest(
            -1880 to 10368,
            arrayOf(Triple(3,2,80),Triple(1,1,25),Triple(6,0,8),Triple(-5,3,50),Triple(1,2,100),Triple(4,0,9),Triple(-8,3,260),Triple(0,1,90)))
    }
}