// https://www.codewars.com/kata/5917a2205ffc30ec3a0000a8/train/kotlin

package kyu_1._7_x_7_skyscrapers.kotlin

import java.util.*

object Skyscrapers {
    fun solvePuzzle(clues: IntArray): Array<IntArray> {
        val game = Game(7, clues)
        return game.solve()
    }
}

internal class Game(private val nBoard: Int, clues: IntArray) {
    private val hintR = (0 ..< nBoard).map{ intArrayOf(clues[(nBoard shl 2) - it - 1], clues[nBoard + it])}.toTypedArray()
    private val hintC = (0 ..< nBoard).map{ intArrayOf(clues[it], clues[nBoard * 3 - it - 1])}.toTypedArray()
    private val board = Array(nBoard) { IntArray(nBoard) }
    private var excludedValues = Array(nBoard) { Array(nBoard){BitSet(nBoard + 1).apply {this.set(1, nBoard + 1 )} } }
    private var change = false

    private fun setValue(r: Int, c: Int, value: Int) {
        if (board[r][c] == value) return
        for (i in 0..<nBoard) excludedValues[r][i].clear(value)
        for (i in 0..<nBoard) excludedValues[i][c].clear(value)
        board[r][c] = value
        excludedValues[r][c].clear()
        excludedValues[r][c].set(value)
        change = true
    }

    private val isResolved: Boolean
        get() {
            for (r in 0..<nBoard) for (c in 0..<nBoard) if (board[r][c] == 0) return false
            return true
        }

    fun solve(): Array<IntArray> {
        solveBasicInit()
        var resolved: Boolean
        do {
            change = false
            solveSetExcludedValues()
            solveOneCellAnalysis()
            solveLineCellAnalysis()
            resolved = isResolved
        } while (!resolved && change)
        if (resolved) return board
        bruteForce()
        return board
    }

    private val isRealExcludedValues: Boolean
        get() {
            for (r in 0..<nBoard) for (c in 0..<nBoard) if (excludedValues[r][c].isEmpty) return false
            return true
        }

    private fun bruteForce() {
        val boardSave = Array(nBoard) { IntArray(nBoard) }
        val excludedValuesSave = Array(nBoard) {
            Array(nBoard){BitSet(nBoard + 1)}}

        for (r in 0..<nBoard) for (c in 0..<nBoard) if (board[r][c] == 0) for (k in 1..nBoard) if (excludedValues[r][c][k]) {
            for (i in 0..<nBoard) boardSave[i] = board[i].clone()
            for (i in 0..<nBoard) for (j in 0..<nBoard) excludedValuesSave[i][j] = excludedValues[i][j].clone() as BitSet
            setValue(r, c, k)
            var resolved: Boolean
            do {
                change = false
                solveSetExcludedValues()
                solveOneCellAnalysis()
                solveLineCellAnalysis()
                resolved = isResolved
            } while (!resolved && change)
            if (resolved) return
            if (isRealExcludedValues) break else {
                for (i in 0..<nBoard) board[i] = boardSave[i].clone()
                for (i in 0..<nBoard) for (j in 0..<nBoard) excludedValues[i][j] = excludedValuesSave[i][j]
            }
        }
    }

    private fun solveBasicInit() {
        for (r in 0..<nBoard) {
            if (hintR[r][0] == nBoard) hintR[r][1] = 1
            if (hintR[r][1] == nBoard) hintR[r][0] = 1
            if (hintR[r][0] == 1) {
                setValue(r, 0, nBoard)
                if (hintR[r][1] == nBoard) for (i in 1..<nBoard) setValue(r, i, nBoard - i)
            }
            if (hintR[r][1] == 1) {
                setValue(r, nBoard - 1, nBoard)
                if (hintR[r][0] == nBoard) for (i in 0..<nBoard - 2) setValue(r, i, i + 1)
            }
        }
        for (c in 0..<nBoard) {
            if (hintC[c][0] == nBoard) hintC[c][1] = 1
            if (hintC[c][1] == nBoard) hintC[c][0] = 1
            if (hintC[c][0] == 1) {
                setValue(0, c, nBoard)
                if (hintC[c][1] == nBoard) for (i in 1..<nBoard) setValue(i, c, nBoard - i)
            }
            if (hintC[c][1] == 1) {
                setValue(nBoard - 1, c, nBoard)
                if (hintC[c][0] == nBoard) for (i in 0..<nBoard - 2) setValue(i, c, i + 1)
            }
        }
    }

    private fun solveSetExcludedValues() {
        val bsM = Array(nBoard){ Array(nBoard){BitSet(nBoard + 1)}}
        for (r in 0..<nBoard) bsM[r] = GenerateLine.getVariants(hintR[r][0], hintR[r][1], board[r], excludedValues[r])
        for (c in 0..<nBoard) {
            val line = IntArray(nBoard)
            val exval = Array(nBoard){BitSet(nBoard + 1)}
            for (r in 0..<nBoard) {
                line[r] = board[r][c]
                exval[r] = excludedValues[r][c]
            }
            val excludedValuesCol = GenerateLine.getVariants(hintC[c][0], hintC[c][1], line, exval)
            for (r in 0..<nBoard) bsM[r][c].and(excludedValuesCol[r])
        }
        excludedValues = bsM
    }

    private fun solveOneCellAnalysis() {
        for (r in 0..<nBoard) for (c in 0..<nBoard)
            if (board[r][c] == 0 && excludedValues[r][c].cardinality() == 1)
                setValue(r, c, excludedValues[r][c].nextSetBit(0))
    }

    private fun solveLineCellAnalysis() {
        for (r in 0..<nBoard) for (k in 1..nBoard) {
            var count = 0
            var index = 0
            var c = 0
            while (c < nBoard) {
                if (board[r][c] == 0 && excludedValues[r][c][k]) {
                    count++
                    if (count == 2) break
                    index = c
                }
                c++
            }
            if (count == 1) setValue(r, index, k)
        }
        for (c in 0..<nBoard) for (k in 1..nBoard) {
            var count = 0
            var index = 0
            var r = 0
            while (r < nBoard) {
                if (board[r][c] == 0 && excludedValues[r][c][k]) {
                    count++
                    if (count == 2) break
                    index = r
                }
                r++
            }
            if (count == 1) setValue(index, c, k)
        }
    }
}

object GenerateLine {
    private var left = 0
    private var right = 0
    private lateinit var values: IntArray
    private lateinit var exval: Array<BitSet>
    private var nLen = 0
    private lateinit var probableValues: Array<BitSet>

    fun getVariants(left: Int, right: Int, values: IntArray, exval: Array<BitSet>): Array<BitSet> {
        this.left = left
        this.right = right
        this.values = values
        this.exval = exval
        nLen = values.size
        probableValues = Array(nLen){BitSet(nLen + 1)}

        if (left == 0 && right == 0) return exval
        val usedValues = BitSet(nLen + 1)
        for (i in values) if (i > 0) usedValues.set(i)
        generateVariant(0, 0, 0, IntArray(nLen), usedValues)
        return probableValues
    }

    private fun generateVariant(cell: Int, v: Int, vmax: Int, rez: IntArray, usedValues: BitSet) {
        var v = v
        var vmax = vmax
        if (v > left && left != 0) return
        if (cell >= nLen) {
            if (v < left && left != 0) return
            if (right != 0) {
                v = 1
                vmax = rez[rez.size - 1]
                for (i in rez.size - 2 downTo -1 + 1) if (vmax < rez[i]) {
                    v++
                    vmax = rez[i]
                }
                if (v != right) return
            }
            for (i in 0..<nLen) probableValues[i].set(rez[i])
            return
        }
        val cellValue = values[cell]
        if (cellValue > 0) {
            rez[cell] = cellValue
            if (vmax < cellValue) {
                v++
                vmax = cellValue
            }
            generateVariant(cell + 1, v, vmax, rez, usedValues)
        } else for (k in 1..nLen)
            if (!usedValues[k] && exval[cell][k]) {
                rez[cell] = k
                usedValues.set(k)
                val oldv = v
                val oldvmax = vmax
                if (vmax < k) {
                    v++
                    vmax = k
                }
                generateVariant(cell + 1, v, vmax, rez, usedValues)
                usedValues.flip(k)
                v = oldv
                vmax = oldvmax
                rez[cell] = 0
            }
    }
}