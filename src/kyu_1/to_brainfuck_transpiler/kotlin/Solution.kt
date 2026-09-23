// https://www.codewars.com/kata/59f9cad032b8b91e12000035/train/kotlin

package kyu_1.to_brainfuck_transpiler.kotlin

import java.util.LinkedList
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.sqrt

sealed class Token() {
    data class Idn      (val name: String) : Token()
    data class NumberLit(val value: Int)   : Token()
    data class StringLit(val text: String) : Token()
    data class CharLit  (val code: Int)    : Token()
    object LBracket : Token()
    object RBracket : Token()
    object EOF      : Token()
    object EOL      : Token()
}

class Lexer(val code: String) {
    private var pos = 0
    private val EOL = '\n'
    private val EOF = '\u0000'

    private fun peek() = if (pos < code.length) code[pos] else '\u0000'
    private fun next() = if (pos < code.length) code[pos++] else '\u0000'
    private fun isIdnStart(c: Char) = c.isLetter() || c == '$' || c == '_'
    private fun isIdnPart(c: Char) = isIdnStart(c) || c.isDigit()

    private fun errorPrint(ch: String, msg: String): Nothing =
        error("$msg --> Unexpected character $ch at ${pos}, [$ch]^^${code.substring(min(code.length, pos), min(code.length, pos + 15))}")

    private fun errorPrint(ch: Char, msg: String): Nothing =
        errorPrint(ch.toString(), msg)

    private fun warpRound(ch: Char): Int {
        val value = peek()
        if (ch == '-' && !value.isDigit()) errorPrint("$ch, $value", "warpRound")
        val sb = StringBuilder()
        while (peek().isDigit()) sb.append(next())
        return sb.toString().toInt().let { (it % 256 + 256) % 256 }
    }

    private fun skipComment() {
        while(" \t\r".contains(peek())) next()
        when (peek()) {
            '-' ->
                if (code.startsWith("--", pos))
                    while (peek() != EOL && peek() != EOF) next()
            '/', '#' ->
                if (code.startsWith("//", pos) ||
                    code.startsWith("#", pos) ||
                    code.toLowerCase().startsWith("rem", pos)
                ) while (peek() != EOL && peek() != EOF) next()
            'r', 'R' ->
                if (code.toLowerCase().startsWith("rem", pos))
                    while (peek() != EOL && peek() != EOF) next()
            else -> return
        }
    }

    private fun syntaxAnalysis(): Token = skipComment().run { when (val ch = peek()) {
        '\u0000' -> Token.EOF
        '\n' -> Token.EOL.also { next() }
        '[' -> Token.LBracket.also { next() }
        ']' -> Token.RBracket.also { next() }
        '"' -> {
            next()
            val sb = StringBuilder()
            while (peek() != EOF && peek() != EOL && peek() != '"') {
                val c = next()
                val p = peek()
                when {
                    c == '\\' && p in listOf('\\', '\'', '"', 'n', 'r', 't') ->
                        sb.append(when (p) {
                            '\\' -> '\\'
                            '\'' -> '\''
                            '"' -> '\"'
                            'n' -> '\n'
                            'r' -> '\r'
                            't' -> '\t'
                            else -> p
                        }).also { next() }
                    else -> sb.append(c)
                }
            }
            if (peek() == '"') next()
            else errorPrint(ch, "Not a string")
            Token.StringLit(sb.toString())
        }
        '\'' -> {
            next()
            val c = next()
            val p = peek()
            next()
            when {
                c != '\\' && p == '\'' -> Token.CharLit(c.toInt())
                c == '\\' && p in listOf('\\', '\'', '"', 'n', 'r', 't') ->
                    if (next() == '\'') {
                        Token.CharLit("\\$p"[0].toInt())
                    } else errorPrint(ch, "Not a char")
                else -> errorPrint(ch, "Unknown character")
            }
        }
        in '0'..'9' -> Token.NumberLit(warpRound(ch))
        '-' -> {
            next()
            Token.NumberLit(-warpRound(ch))
        }
        else -> if (isIdnStart(ch)) {
            val sb = StringBuilder()
            while (isIdnPart(peek())) sb.append(next())
            Token.Idn(sb.toString().toLowerCase())
        } else errorPrint(ch, "Unknown character")
    } }

    fun tokenize(): List<Token> {
        val tokens = mutableListOf<Token>()
        while (pos < code.length) {
            tokens += syntaxAnalysis()
        }
        return tokens.also{ it.add(Token.EOF) }
    }
}

sealed class Stmt {
    data class VarDecl(val names: List<VarSingle>) : Stmt()
    data class Set(val a: VarSingle.Simple, val b: Expr) : Stmt()
    data class Inc(val a: String, val b: Expr) : Stmt()
    data class Dec(val a: String, val b: Expr) : Stmt()
    data class Add(val x: Expr, val y: Expr, val dest: String) : Stmt()
    data class Sub(val x: Expr, val y: Expr, val dest: String) : Stmt()
    data class Mul(val x: Expr, val y: Expr, val dest: String) : Stmt()
    data class DivMod(val x: Expr, val y: Expr, val q: String, val r: String) : Stmt()
    data class Div(val x: Expr, val y: Expr, val dest: String) : Stmt()
    data class Mod(val x: Expr, val y: Expr, val dest: String) : Stmt()
    data class Cmp(val x: Expr, val y: Expr, val dest: String) : Stmt()
    data class A2B(val a: Expr, val b: Expr, val c: Expr, val d: String) : Stmt()
    data class B2A(val a: Expr, val b: String, val c: String, val d: String) : Stmt()
    data class LSet(val name: String, val idx: Expr, val v: Expr) : Stmt()
    data class LGet(val name: String, val idx: Expr, val dest: String) : Stmt()
    data class IfEq(val a: String, val b: Expr, val body: List<Stmt>) : Stmt()
    data class IfNeq(val a: String, val b: Expr, val body: List<Stmt>) : Stmt()
    data class WNeq(val a: String, val b: Expr, val body: List<Stmt>) : Stmt()
    data class Proc(val name: String, val params: List<String>, val body: List<Stmt>) : Stmt()
    data class Call(val name: String, val args: List<String>) : Stmt()
    data class Read(val a: String) : Stmt()
    data class Msg(val parts: List<MsgPart>) : Stmt()
}

sealed class Expr {
    data class Var(val name: String) : Expr()
    data class Num(val value: Int) : Expr()
}

sealed class VarSingle {
    data class Simple(val name: String) : VarSingle()
    data class List(val name: String, val size: Int) : VarSingle()
}

sealed class MsgPart {
    data class Str(val text: String) : MsgPart()
    data class ExprVal(val e: Expr) : MsgPart()
}

class Parser(private val tokens: List<Token>){
    private var pos = 0
    private fun peek() = tokens.getOrElse(pos) { Token.EOF }
    private fun next() = tokens.getOrElse(pos++) { Token.EOF }

    private fun skipEOL() { while (peek() is Token.EOL) next() }
    private fun skipAndPeek(): Token = skipEOL().run { peek() }

    fun parse(): List<Stmt> = mutableListOf<Stmt>().apply {
        while (peek() !is Token.EOF) {
            if (skipAndPeek() is Token.EOF) break
            add(parseStmt())
        }
    }

    private fun getIdnName(): String = when (val t = next()) {
        is Token.Idn -> t.name
        else -> error("Expected identifier at $pos, but found $t")
    }

    private fun getIdnNames(): List<String> = mutableListOf<String>().apply {
        while (peek() is Token.Idn) {
            val idn = getIdnName()
            if (contains(idn)) error("Duplicate parameter names")
            add(idn)
        }
    }

    private fun expect(tok: Token) {
        val t = next()
        if (t != tok) error("Unclosed [] pair")
    }

    private fun getIdnListSize(): Int {
        expect(Token.LBracket)
        val size = when (val t = next()) {
            is Token.NumberLit -> t.value
            else -> error("Expected number in $pos, but found $t")
        }
        expect(Token.RBracket)
        return size
    }

    private fun parseExpr(): Expr = when (val t = next()) {
        is Token.NumberLit -> Expr.Num(t.value)
        is Token.CharLit -> Expr.Num(t.code)
        is Token.Idn -> Expr.Var(t.name)
        else -> error("Expected expression at $pos, but found $t")
    }

    private fun atBlockEnd(): Boolean = when (val t = peek()) {
        is Token.Idn       -> t.name == "end"
        else               -> error("Unclosed blocks")
    }

    private fun getStmtBody(): List<Stmt> = mutableListOf<Stmt>().apply {
        while (true) {
            skipEOL()
            if (atBlockEnd()) break
            val stmt = parseStmt()
            if (stmt is Stmt.VarDecl) error("Define variables inside a procedure")
            if (stmt is Stmt.Proc) error("Nested procedures")
            add(stmt)
        }
    }

    private fun parseStmt(): Stmt {
        val tok = skipAndPeek()
        if (tok !is Token.Idn) error("Statement must start with a keyword at $pos, but found $tok")
        return when ((next() as Token.Idn).name) {
            "var" -> Stmt.VarDecl(mutableListOf<VarSingle>().apply {
                while (peek() is Token.Idn) {
                    val name = getIdnName()
                    val varNode = if (peek() is Token.LBracket)
                        VarSingle.List(name, getIdnListSize())
                    else
                        VarSingle.Simple(name)
                    add(varNode)
                }
                if (peek() !is Token.Idn && peek() != Token.EOL && peek() != Token.EOF)
                    error("Expect a variable but got something else")
            })
            "set" -> Stmt.Set(VarSingle.Simple(getIdnName()), parseExpr())
            "inc" -> Stmt.Inc(getIdnName(), parseExpr())
            "dec" -> Stmt.Dec(getIdnName(), parseExpr())
            "add" -> Stmt.Add(parseExpr(), parseExpr(), getIdnName())
            "sub" -> Stmt.Sub(parseExpr(), parseExpr(), getIdnName())
            "mul" -> Stmt.Mul(parseExpr(), parseExpr(), getIdnName())
            "divmod" -> Stmt.DivMod(parseExpr(), parseExpr(), getIdnName(), getIdnName())
            "div" -> Stmt.Div(parseExpr(), parseExpr(), getIdnName())
            "mod" -> Stmt.Mod(parseExpr(), parseExpr(), getIdnName())
            "cmp" -> Stmt.Cmp(parseExpr(), parseExpr(), getIdnName())
            "a2b" -> Stmt.A2B(parseExpr(), parseExpr(), parseExpr(), getIdnName())
            "b2a" -> Stmt.B2A(parseExpr(), getIdnName(), getIdnName(), getIdnName())
            "lset" -> Stmt.LSet(getIdnName(), parseExpr(), parseExpr())
            "lget" -> Stmt.LGet(getIdnName(), parseExpr(), getIdnName())
            "ifeq" -> Stmt.IfEq(getIdnName(), parseExpr(), getStmtBody()).also { if (atBlockEnd()) next() }
            "ifneq" -> Stmt.IfNeq(getIdnName(), parseExpr(), getStmtBody()).also { if (atBlockEnd()) next() }
            "wneq" -> Stmt.WNeq(getIdnName(), parseExpr(), getStmtBody()).also { if (atBlockEnd()) next() }
            "proc" -> Stmt.Proc(getIdnName(), getIdnNames(), getStmtBody()).also { if (atBlockEnd()) next() }
            "call" -> Stmt.Call(getIdnName(), getIdnNames())
            "read" -> Stmt.Read(getIdnName())
            "msg" -> Stmt.Msg(mutableListOf<MsgPart>().apply {
                var flag = true
                while (flag) when (val t = next()) {
                    is Token.StringLit -> add(MsgPart.Str(t.text))
                    is Token.CharLit -> add(MsgPart.Str(t.code.toChar().toString()))
                    is Token.NumberLit -> add(MsgPart.Str(t.value.toChar().toString()))
                    is Token.Idn -> add(MsgPart.ExprVal(Expr.Var(t.name)))
                    else -> flag = false
                }
            })
            else -> error("Unknown instructions")
        }
    }
}

private class BFBuilder {
    private val sb = StringBuilder()
    var ptr: Int = 0
        private set

    fun code(): String = sb.toString()

    fun moveRel(delta: Int) {
        if (delta > 0) repeat(delta) { sb.append('>') } else repeat(-delta) { sb.append('<') }
        ptr += delta
    }
    fun moveTo(cell: Int) {
        moveRel(cell - ptr)
    }
    fun plus(n: Int) {
        if (n > 0) repeat(n) { sb.append('+') } else repeat(-n) { sb.append('-') }
    }
    fun clear() { sb.append("[-]") }
    fun setConst(v: Int) { clear(); if (v != 0) plus(v) }
    fun out() { sb.append('.') }
    fun input() { sb.append(',') }
    fun open() { sb.append('[') }
    fun close() { sb.append(']') }
    fun slidingRight() { sb.append("[[->+<]+>-]") }
    fun clearLeft2Zero() { sb.append("+[[-]<]") }
    fun addCode(code: String) { sb.append(code) }
}

private class Mem {
    data class Arr(val base: Int, val size: Int)

    private val vars = mutableMapOf<String, Int>()
    private val tmpVars = mutableMapOf<String, Int>()
    private val arrays = mutableMapOf<String, Arr>()
    private val aliasStack = LinkedList<MutableMap<String, Int>>()
    private var allocable = true
    private var nextCell = 0
    private var nextTempCell = 0

    val T0 = allocTemp()
    val T1 = allocTemp()
    val T2 = allocTemp()
    val T3 = allocTemp()
    val T4 = allocTemp()
    val T5 = allocTemp()
    val T6 = allocTemp()
    val T7 = allocTemp()

    private fun allocTemp(): Int = nextCell++

    fun pushAlias(map: Map<String, Int>) { aliasStack.addLast(map.toMutableMap()) }
    fun popAlias() { aliasStack.removeLast() }

    private fun resolveAlias(name: String): Int? {
        for (frame in aliasStack.asReversed()) {
            frame[name]?.let { return it }
        }
        return null
    }

    private fun containsKey(key: String): Boolean =
        (vars.keys union arrays.keys).any { it.equals(key, ignoreCase = true) }

    fun allocVar(name: String): Int {
        if (!allocable) return -1
        resolveAlias(name)?.let { return it }
        if (containsKey(name)) error("Duplicate var names")
        val id = nextCell++
        nextTempCell = nextCell
        vars[name] = id
        return id
    }

    fun getVar(name: String): Int {
        resolveAlias(name)?.let { return it }
        return vars[name] ?: error("Undefined var names")
    }

    fun allocTempVar(name: String): Int {
        if (allocable) return -1
        resolveAlias(name)?.let { return it }
        if (tmpVars.containsKey(name)) error("Duplicate var names")
        val id = nextTempCell++
        tmpVars[name] = id
        return id
    }

    fun allocArray(name: String, size: Int): Arr {
        if (!allocable) return Arr(-1, -1)
        if (containsKey(name)) error("Duplicate var names")
        val base = nextCell
        nextCell += size
        nextTempCell = nextCell
        val arr = Arr(base, size)
        arrays[name] = arr
        return arr
    }

    fun getArray(name: String): Arr =
        arrays[name] ?: error("Undefined list: $name")

    fun getArraySize(name: String) =
        (getArray(name).size - 2) / 2

    fun lockAlloc() {
        allocable = false
    }

    fun freeTmpAlloc(): Int {
        tmpVars.clear()
        nextTempCell = nextCell
        return nextTempCell
    }
}

private class BFCodegen {
    private val bf = BFBuilder()
    private val mem = Mem()
    private val procs = mutableMapOf<String, Stmt.Proc>()

    private fun wrap(x: Int): Int = ((x % 256) + 256) % 256

    fun generate(program: List<Stmt>): String {
        program.filter { it -> it is Stmt.Proc }.forEach { proc -> emitStmt(proc) }
        program.filter { it -> it !is Stmt.Proc }.forEach { all ->
            if (all !is Stmt.VarDecl) mem.lockAlloc()
            emitStmt(all)
        }
        return bf.code()
    }

    private fun goto(cell: Int) = bf.moveTo(cell)
    private fun clear(cell: Int) { goto(cell); bf.clear() }
    private fun setConst(cell: Int, v: Int) { goto(cell); bf.setConst(v) }
    private fun add(cell: Int, delta: Int) { goto(cell); bf.plus(delta) }
    private fun putChar(cell: Int) { goto(cell); bf.out(); clear(cell) }

    private inline fun isNonZero(cell: Int, body: () -> Unit) {
        goto(cell)
        bf.open()
        body()
        bf.close()
    }

    private inline fun whileLoop(cond: Int, body: () -> Unit) {
        isNonZero(cond) {
            body()
            goto(cond)
        }
    }

    private inline fun ifNonZeroOnce(cell: Int, body: () -> Unit) {
        val tmp = mem.T5
        if (cell != tmp) copyTo(cell, tmp)
        isNonZero(tmp) {
            bf.clear()
            body()
            goto(tmp)
        }
    }

    private inline fun isZero(cell: Int, body: () -> Unit) {
        val gate = mem.T7
        setConst(gate, 1)
        ifNonZeroOnce(cell) {
            clear(gate)
        }
        isNonZero(gate) {
            body()
            clear(gate)
        }
    }

    private fun compare(left: Int, right: Int, dst: Int) {
        val a = mem.T2
        val b = mem.T3
        if (left != a) copyTo(left, a)
        if (right != b) copyTo(right, b)
        clear(dst)

        whileLoop(a) {
            bf.plus(-1)
            isZero(b) {
                setConst(dst, 1)
                clear(a)
            }
            decOnceIfNonZero(b)
        }

        ifNonZeroOnce(b) {
            setConst(dst, -1)
            clear(b)
        }
    }

    private inline fun isEqual(a: Int, b: Int, body: () -> Unit) {
        val err = mem.T4
        compare(a, b, err)
        isZero(err) {
            body()
        }
    }

    private inline fun isNotEqual(a: Int, b: Int, body: () -> Unit) {
        val err = mem.T4
        compare(a, b, err)
        isNonZero(err) {
            body()
            clear(err)
        }
    }

    private fun decOnceIfNonZero(cell: Int) {
        ifNonZeroOnce(cell) {
            add(cell, -1)
        }
    }

    private fun isGreater(left: Int, right: Int, body: () -> Unit) {
        val err = mem.T4
        compare(left, right, err)
        add(err, -1)
        isZero(err) {
            body()
        }
    }

    private fun isLess(left: Int, right: Int, body: () -> Unit) {
        val err = mem.T4
        compare(left, right, err)
        add(err, 1)
        isZero(err) {
            body()
        }
    }

    private fun addTo(src: Int, dst: Int, tmp: Int, isAdd: Boolean = true) {
        val v = if (isAdd) 1 else -1
        isNonZero(src) {
            bf.plus(-1)
            add(dst, v)
            add(tmp, 1)
            goto(src)
        }
        isNonZero(tmp) {
            bf.plus(-1)
            add(src, 1)
            goto(tmp)
        }
    }

    private fun subTo(src: Int, dst: Int, tmp: Int) {
        addTo(src, dst, tmp, false)
    }

    private fun copyTo(src: Int, dst: Int) {
        if (src == dst) return
        val tmp = mem.T0
        clear(dst)
        clear(tmp)
        addTo(src, dst, tmp)
    }

    private fun addFrom(dst: Int, src: Int) {
        val tmp = mem.T0
        clear(tmp)
        addTo(src, dst, tmp)
    }

    private fun subFrom(dst: Int, src: Int) {
        val tmp = mem.T0
        clear(tmp)
        subTo(src, dst, tmp)
    }

    private fun opFrom(x: Expr, y: Expr, dst: Int, op: Char) {
        val a = mem.allocTempVar("opFromA")
        val b = mem.allocTempVar("opFromB")
        evalExprInto(x, a)
        evalExprInto(y, b)
        clear(dst)
        opFrom(a, b, dst, op)
        clear(a)
        clear(b)
        mem.freeTmpAlloc()
    }

    private fun opFrom(x: Int, y: Int, dst: Int, op: Char) {
        val a = mem.T2
        val b = mem.T3
        if (a == x || b == y) error("op mem overflow")
        copyTo(x, a)
        copyTo(y, b)
        clear(dst)
        when (op) {
            '+' -> {
                addFrom(dst, a)
                addFrom(dst, b)
            }
            '-' -> {
                addFrom(dst, a)
                subFrom(dst, b)
            }
            '*' -> {
                whileLoop(b) {
                    add(b, -1)
                    addFrom(dst, a)
                }
            }
            '/' -> {
                val tmp = mem.T1
                val gate = mem.T6
                setConst(gate, 1)
                copyTo(a, tmp)
                whileLoop(gate) {
                    copyTo(tmp, a)
                    copyTo(y, b)
                    isLess(a, b) {
                        clear(tmp)
                        clear(gate)
                    }
                    ifNonZeroOnce(gate) {
                        add(dst, 1)
                        copyTo(y, b)
                        subFrom(tmp, b)
                    }
                }
            }
            '%' -> {
                val tmp = mem.T1
                val gate = mem.T6
                setConst(gate, 1)
                copyTo(a, tmp)
                whileLoop(gate) {
                    copyTo(tmp, a)
                    copyTo(y, b)
                    isLess(a, b) {
                        copyTo(tmp, dst)
                        clear(tmp)
                        clear(gate)
                    }
                    ifNonZeroOnce(gate) {
                        copyTo(y, b)
                        subFrom(tmp, b)
                    }
                }
            }
        }
    }

    private fun inListIdxLock(idxFlag: Int, arrL: Int) {
        goto(idxFlag)
        bf.slidingRight()
        bf.moveRel((arrL+1))
    }

    private fun outListIdxUnlock(arrL: Int) {
        bf.moveRel(-(arrL+1))
        bf.clearLeft2Zero()
        bf.addCode(">")
    }

    private fun gotoListIdx(idxFlag: Int, arrL: Int, body: () -> Unit) {
        inListIdxLock(idxFlag, arrL)
        body()
        outListIdxUnlock(arrL)
    }

    private fun emitOptPlusTo(dst: Int, targetVal: Int) {
        val count = mem.T6
        emitOptPlusToTmp(count, dst, targetVal)
    }

    private fun emitOptPlusToTmp(count: Int, dst: Int, targetVal: Int) {
        val loopTimes = floor(sqrt(targetVal.toDouble())).toInt().coerceAtLeast(1)
        val perLoopAdd = targetVal / loopTimes
        val remainder = targetVal % loopTimes

        setConst(count, loopTimes)
        clear(dst)

        whileLoop(count) {
            add(count, -1)
            add(dst, perLoopAdd)
        }

        if (remainder > 0) {
            add(dst, remainder)
        }

        clear(count)
    }

    private fun evalExprInto(e: Expr, target: Int) {
        when (e) {
            is Expr.Num -> emitOptPlusTo(target, wrap(e.value))
            is Expr.Var -> copyTo(mem.getVar(e.name), target)
        }
    }

    private fun evalExprInto(counter: Int, e: Expr, target: Int) {
        when (e) {
            is Expr.Num -> emitOptPlusToTmp(counter, target, wrap(e.value))
            is Expr.Var -> copyTo(mem.getVar(e.name), target)
        }
    }

    private fun emitStmt(stmt: Stmt) {
        when (stmt) {
            is Stmt.VarDecl -> {
                for (v in stmt.names) {
                    when (v) {
                        is VarSingle.Simple -> mem.allocVar(v.name)
                        is VarSingle.List -> mem.allocArray(v.name, 1 + v.size + 1 + v.size)
                    }
                }
            }
            is Stmt.Set -> {
                val dst = mem.getVar(stmt.a.name)
                evalExprInto(stmt.b, dst)
            }
            is Stmt.Inc -> {
                val dst = mem.getVar(stmt.a)
                val tmp = mem.T2
                evalExprInto(stmt.b, tmp)
                addFrom(dst, tmp)
            }
            is Stmt.Dec -> {
                val dst = mem.getVar(stmt.a)
                val tmp = mem.T2
                evalExprInto(stmt.b, tmp)
                subFrom(dst, tmp)
            }
            is Stmt.Add -> {
                val dst = mem.getVar(stmt.dest)
                opFrom(stmt.x, stmt.y, dst, '+')
            }
            is Stmt.Sub -> {
                val dst = mem.getVar(stmt.dest)
                opFrom(stmt.x, stmt.y, dst, '-')
            }
            is Stmt.Mul -> {
                val dst = mem.getVar(stmt.dest)
                opFrom(stmt.x, stmt.y, dst, '*')
            }
            is Stmt.DivMod -> {
                val x = mem.allocTempVar("DivModX")
                val y = mem.allocTempVar("DivModY")
                val dstQ = mem.getVar(stmt.q)
                val dstR = mem.getVar(stmt.r)
                evalExprInto(stmt.x, x)
                evalExprInto(stmt.y, y)
                opFrom(x, y, dstQ, '/')
                opFrom(x, y, dstR, '%')
                clear(x)
                clear(y)
                mem.freeTmpAlloc()
            }
            is Stmt.Div -> {
                val dst = mem.getVar(stmt.dest)
                opFrom(stmt.x, stmt.y, dst, '/')
            }
            is Stmt.Mod -> {
                val dst = mem.getVar(stmt.dest)
                opFrom(stmt.x, stmt.y, dst, '%')
            }
            is Stmt.Cmp -> {
                val a = mem.T2
                val b = mem.T3
                val dst = mem.getVar(stmt.dest)
                evalExprInto(stmt.x, a)
                evalExprInto(stmt.y, b)
                compare(a, b, dst)
            }
            is Stmt.A2B -> {
                val a = mem.allocTempVar("A2Ba")
                val b = mem.allocTempVar("A2Bb")
                val c = mem.allocTempVar("A2Bc")
                evalExprInto(stmt.a, a)
                evalExprInto(stmt.b, b)
                evalExprInto(stmt.c, c)
                val d = mem.getVar(stmt.d)
                val dst = mem.allocTempVar("A2BDst")
                val tmp = mem.allocTempVar("A2BTmp")
                emitOptPlusTo(tmp, wrap(-48))
                clear(d)
                addFrom(a, tmp)
                addFrom(b, tmp)
                addFrom(c, tmp)
                emitOptPlusTo(tmp, 100)
                opFrom(a, tmp, dst, '*')
                addFrom(d, dst)
                emitOptPlusTo(tmp, 10)
                opFrom(b, tmp, dst, '*')
                addFrom(d, dst)
                emitOptPlusTo(tmp, 1)
                opFrom(c, tmp, dst, '*')
                addFrom(d, dst)
                clear(a)
                clear(b)
                clear(c)
                clear(dst)
                clear(tmp)
                mem.freeTmpAlloc()
            }
            is Stmt.B2A -> {
                val a = mem.allocTempVar("B2ASrc")
                val b = mem.allocTempVar("B2Ab")
                val c = mem.allocTempVar("B2Ac")
                val d = mem.allocTempVar("B2Ad")
                val dst = mem.allocTempVar("B2ADst")
                val tmp = mem.allocTempVar("B2ATmp")
                emitOptPlusTo(tmp, 48)
                addFrom(b, tmp)
                addFrom(c, tmp)
                addFrom(d, tmp)
                evalExprInto(stmt.a, a)
                emitOptPlusTo(tmp, 100)
                opFrom(a, tmp, dst, '/')
                addFrom(b, dst)
                evalExprInto(stmt.a, a)
                emitOptPlusTo(tmp, 10)
                opFrom(a, tmp, dst, '/')
                copyTo(dst, a)
                emitOptPlusTo(tmp, 10)
                opFrom(a, tmp, dst, '%')
                addFrom(c, dst)
                evalExprInto(stmt.a, a)
                emitOptPlusTo(tmp, 10)
                opFrom(a, tmp, dst, '%')
                addFrom(d, dst)
                copyTo(b, mem.getVar(stmt.b))
                copyTo(c, mem.getVar(stmt.c))
                copyTo(d, mem.getVar(stmt.d))
                clear(a)
                clear(b)
                clear(c)
                clear(d)
                clear(dst)
                clear(tmp)
                mem.freeTmpAlloc()
            }
            is Stmt.LSet -> {
                val arr = mem.getArray(stmt.name)
                val arrL = mem.getArraySize(stmt.name)
                if (stmt.idx is Expr.Num) {
                    val targetPtr = arr.base + 1 + arrL + 1 + stmt.idx.value
                    evalExprInto(stmt.v, targetPtr)
                }
                else {
                    val tmp = mem.T2
                    val idx = mem.T3
                    evalExprInto(stmt.idx, idx)
                    setConst(tmp, arrL)
                    isGreater(tmp, idx) {
                        val idxFlag = arr.base + 1
                        evalExprInto(stmt.v, tmp)
                        evalExprInto(stmt.idx, idxFlag)
                        whileLoop(tmp) {
                            bf.plus(-1)
                            gotoListIdx(idxFlag, arrL) {
                                bf.plus(1)
                            }
                            evalExprInto(stmt.idx, idxFlag)
                        }
                        clear(idxFlag)
                    }
                }
            }
            is Stmt.LGet -> {
                val arr = mem.getArray(stmt.name)
                val arrL = mem.getArraySize(stmt.name)
                val dst = mem.getVar(stmt.dest)
                clear(dst)
                if (stmt.idx is Expr.Num) {
                    val src = arr.base + 1 + arrL + 1 + stmt.idx.value
                    copyTo(src, dst)
                }
                else {
                    val tmp = mem.T2
                    val idx = mem.T3
                    evalExprInto(stmt.idx, idx)
                    setConst(tmp, arrL)
                    isGreater(tmp, idx) {
                        val idxFlag = arr.base + 1
                        evalExprInto(stmt.idx, idxFlag)
                        inListIdxLock(idxFlag, arrL)
                        whileLoop(bf.ptr) {
                            bf.plus(-1)
                            outListIdxUnlock(arrL)
                            add(idx, 1)
                            add(tmp, 1)
                            evalExprInto(stmt.idx, idxFlag)
                            inListIdxLock(idxFlag, arrL)
                        }
                        outListIdxUnlock(arrL)
                        evalExprInto(stmt.idx, idxFlag)
                        whileLoop(tmp) {
                            bf.plus(-1)
                            gotoListIdx(idxFlag, arrL) {
                                bf.plus(1)
                            }
                            evalExprInto(stmt.idx, idxFlag)
                        }
                        clear(idxFlag)
                        whileLoop(idx) {
                            bf.plus(-1)
                            add(dst, 1)
                        }
                    }
                }
            }
            is Stmt.IfEq -> {
                val a = mem.getVar(stmt.a)
                val b = mem.T3
                evalExprInto(stmt.b, b)
                isEqual(a, b) {
                    stmt.body.forEach { emitStmt(it) }
                }
            }
            is Stmt.IfNeq -> {
                val a = mem.getVar(stmt.a)
                val b = mem.T3
                evalExprInto(stmt.b, b)
                isNotEqual(a, b) {
                    stmt.body.forEach { emitStmt(it) }
                }
            }
            is Stmt.WNeq -> {
                val gate = mem.T1
                val v = mem.getVar(stmt.a)
                val a = mem.T2
                val b = mem.T3
                copyTo(v, a)
                evalExprInto(stmt.b, b)
                setConst(gate, 1)
                whileLoop(gate) {
                    isEqual(a, b) {
                        clear(gate)
                    }
                    copyTo(v, a)
                    evalExprInto(stmt.b, b)
                    isNotEqual(a, b) {
                        stmt.body.forEach { emitStmt(it) }
                    }
                    copyTo(v, a)
                    evalExprInto(stmt.b, b)
                }
                clear(a)
                clear(b)
            }
            is Stmt.Proc -> {
                if (procs.containsKey(stmt.name)) error("Duplicate procedure: ${stmt.name}")
                procs[stmt.name] = stmt
            }
            is Stmt.Call -> {
                val def = procs[stmt.name] ?: error("Undefined procedure: ${stmt.name}")
                if (def.params.size != stmt.args.size) error("Argument number mismatch for ${stmt.name}")
                val alias = def.params.zip(stmt.args).associate { (p, a) -> p to mem.getVar(a) }
                mem.pushAlias(alias)
                def.body.forEach { emitStmt(it) }
                mem.popAlias()
            }
            is Stmt.Read -> {
                val dst = mem.getVar(stmt.a)
                goto(dst); bf.input()
            }
            is Stmt.Msg -> {
                for (p in stmt.parts) {
                    when (p) {
                        is MsgPart.Str -> p.text.forEach { ch ->
                            val tmp = mem.T7
                            emitOptPlusTo(tmp, ch.toInt())
                            putChar(tmp)
                        }
                        is MsgPart.ExprVal -> {
                            val tmp = mem.allocTempVar("tmpChar")
                            val counter = mem.allocTempVar("tmpCount")
                            evalExprInto(counter, p.e, tmp)
                            putChar(tmp)
                            clear(tmp)
                            mem.freeTmpAlloc()
                        }
                    }
                }
            }
        }
    }
}

fun optimizeBrainfuck(code: String): String {
    val sb = StringBuilder()
    var i = 0
    val len = code.length

    val cleanCode = fun(op1: Char, op2: Char) {
        var net = 0
        while (i < len && (code[i] == op1 || code[i] == op2)) {
            net += if (code[i] == op1) 1 else -1
            i++
        }
        if (net != 0) {
            val signChar = if (net > 0) op1 else op2
            repeat(abs(net)) { sb.append(signChar) }
        }
    }
    while (i < len) {
        when (val ch = code[i]) {
            '+', '-' -> cleanCode('+', '-')
            '>', '<' -> cleanCode('>', '<')
            else -> {
                sb.append(ch)
                i++
            }
        }
    }

    var optimized = sb.toString()

    val patterns = listOf(
        Regex("><|<>"),
        Regex("\\[-](\\[-])+"),
        Regex("[+-]+\\[-]")
    )

    patterns.forEach { pattern ->
        optimized = optimized.replace(pattern, "[-]")
    }

    optimized = optimized.replace(Regex("\\[\\]"), "")

    return optimized
}

fun kcuf(code: String): String {
    val tokens = Lexer(code).tokenize()
    val ast = Parser(tokens).parse()
    val codegen = BFCodegen()
    val bfCode = codegen.generate(ast)
    val cleanCode = optimizeBrainfuck(bfCode)
    return cleanCode
}

