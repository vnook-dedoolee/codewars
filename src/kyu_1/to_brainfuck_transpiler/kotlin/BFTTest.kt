package kyu_1.to_brainfuck_transpiler.kotlin

import org.junit.Test
import org.junit.Assert.*
import org.junit.FixMethodOrder
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class BFTTest
{
	fun Check(_RawCode : String,Input : String = "",Expect : String = "",Message : String = "")
	{
		val RawCode = _RawCode.trimIndent()
		println(RawCode)
		println("<b>Input :</b> ${Input.toCharArray().map{it.toInt()}}")
		println("<b>Expected output :</b> ${Expect.toCharArray().map{it.toInt()}}")
		val Code = kcuf(RawCode)
		println("<b>Output code length :</b> ${Code.length}")
		assertEquals(Message,Expect,Execute(Code,Input))
	}

	@Test
	fun `FixedTest 0 | Basic 0 | Works for var, read, msg, comment`()
	{
		Check("""
		var X//This is a comment
		read X--This is also a comment
		msg "Bye" X#No doubt it is a comment
		rem &&Some comment~!@#$":<
		""","?","Bye?")
	}
	@Test
	fun `FixedTest 0 | Basic 1 | Works for set, inc, dec`()
	{
		Check("""
		var A B
		sEt A 'a'
		msg a B
		set B 50
		msG A b
		inc A 10
		dec B -20
		msg A B
		""","","a\u0000a2kF")
	}
	@Test
	fun `FixedTest 0 | Basic 2 | Works for kinds of numbers`()
	{
		Check("""
		var X
		set X  114514
		msg X
		set X -114514
		msg X
		set X 'X'
		msg X
		""","","\u0052\u00ae\u0058")
	}
	@Test
	fun `FixedTest 0 | Basic 3 | Works for add, sub, mul`()
	{
		Check("""
		var A B C
		read A
		read B
		add a b c
		msg a b c
		sub a b a
		msg a b c
		mul b a c
		msg a b c
		""","0\u0007","\u0030\u0007\u0037\u0029\u0007\u0037\u0029\u0007\u001f")
	}
	@Test
	fun `FixedTest 0 | Basic 4 | Works for divmod, div, mod`()
	{
		Check("""
		var A B C D
		set A 79
		set B 13
		divmod A B C D
		msg A B C D
		div C D C
		msg A B C D
		mod A D A
		msg A B C D
		""","","\u004f\u000d\u0006\u0001\u004f\u000d\u0006\u0001\u0000\u000d\u0006\u0001")
	}
	@Test
	fun `FixedTest 0 | Basic 5 | Works for cmp`()
	{
		Check("""
		var X K
		read X
		cmp 80 X K
		msg X K
		cmp X 'z' K
		msg X K
		cmp X X K
		msg X K
		""","\u0080","\u0080\u00ff\u0080\u0001\u0080\u0000")
	}
	@Test
	fun `FixedTest 0 | Basic 6 | Works for a2b, b2a`()
	{
		Check("""
		var A B C D
		set a 247
		b2a A B C D
		msg A B C D
		inc B 1
		dec C 2
		inc D 5
		a2b B C D A
		msg A B C D // A = (100 * (2 + 1) + 10 * (4 - 2) + (7 + 5)) % 256 = 76 = 0x4c
		""","","\u00f7\u0032\u0034\u0037\u004c\u0033\u0032\u003c")
	}
	@Test
	fun `FixedTest 0 | Basic 7 | Works for lset, lget`()
	{
		Check("""
		var L  [ 20 ]  I X
		lset L 10 80
		set X 20
		lset L 5 X
		set X 9
		lset L X X
		set I 4
		lget L I X
		msg X
		lget L 5 X
		msg X
		lget L 9 X
		msg X
		lget L 10 X
		msg X
		lget L 19 X
		msg X
		""","","\u0000\u0014\u0009\u0050\u0000")
	}
	@Test
	fun `FixedTest 0 | Basic 8 | Works for ifeq, ifneq, wneq`()
	{
		Check("""
		var F L[5] X
		set F 0
		add 10 10 X
		wneq F 5
			lset L F X
			inc F 1
			dec X 1
		end
		//L == [20,19,18,17,16]

		wneq F 0
			inc F -1
			lget L F X
			msg X
		end

		set F 10
		wneq F 0
			ifeq F 10
				set F 5
			end
			dec F 1
			lget L F X
			ifneq X 18
				msg F X
			end
		end
		ifeq F 0
			ifneq X 50
				msg ";-)"
			end
		end
		""","","\u0010\u0011\u0012\u0013\u0014\u0004\u0010\u0003\u0011\u0001\u0013\u0000\u0014;-)")
	}
	@Test
	fun `FixedTest 0 | Basic 9 | Works for proc`()
	{
		Check("""
		var A B T
		set A 'U'
		set B 'V'

		msg"Outer Before : "A B"\n"
		call swap B A
		msg"Outer After : "A B"\n"

		proc swap x y
			msg "Inner Before : "x y"\n"
			set T x
			call say T
			set x y
			set y T
			msg "Inner After : "x y"\n"
		end
		proc say x
			msg "It is " x " now\n"
		end
		""","","Outer Before : UV\n" +
			"Inner Before : VU\n" +
			"It is V now\n" +
			"Inner After : UV\n" +
			"Outer After : VU\n")
	}



	fun ErrorWhen(Desc : String,Code : String)
	{
		var Thrown = false
		try{Check(Code,"","","")}
		catch(e : Throwable)
		{
			Thrown = true
			println("<b>Expected error was thrown :</b> ${e.message}")
		}
		assertTrue("An error is expected caused by : $Desc",Thrown);
	}
	@Test
	fun `FixedTest 1 | Invalid 00 | Unknown instructions`()
	{
		ErrorWhen("Unknown instructions","""
		var a
		mov a 5
		""")
	}
	@Test
	fun `FixedTest 1 | Invalid 01 | Arguments for an instruction are too much or not enough`()
	{
		ErrorWhen("Arguments for an instruction are too much or not enough","""
		var x
		set x
		""")
	}
	@Test
	fun `FixedTest 1 | Invalid 02 | Undefined var names`()
	{
		ErrorWhen("Undefined var names","""
		msg x
		""")
	}
	@Test
	fun `FixedTest 1 | Invalid 03 | Duplicate var names`()
	{
		ErrorWhen("Duplicate var names","""
		var Q
		var q[20]
		""")
	}
	@Test
	fun `FixedTest 1 | Invalid 04 | Define variables inside a procedure`()
	{
		ErrorWhen("Define variables inside a procedure","""
		proc nice
			var evil
		end
		""")
	}
	@Test
	fun `FixedTest 1 | Invalid 05 | Unclosed square bracket pair`()
	{
		ErrorWhen("Unclosed [] pair","""
		var x[60 Y
		""")
	}
	@Test
	fun `FixedTest 1 | Invalid 06 | Expect a variable but got something else`()
	{
		ErrorWhen("Expect a variable but got something else","""
		var c 20
		set 20 20
		add "what" 'x' c
		// all lines above cause this error respectively
		""")
	}
	@Test
	fun `FixedTest 1 | Invalid 07 | Expect a variable but got a list`()
	{
		ErrorWhen("Expect a variable but got a list","""
		var L[40] X[20]
		LSet L 0 X
		""")
	}
	@Test
	fun `FixedTest 1 | Invalid 08 | Expect a list but got a variable`()
	{
		ErrorWhen("Expect a list but got a variable","""
		var L X
		LGet L 0 X
		""")
	}
	@Test
	fun `FixedTest 1 | Invalid 09 | Unclosed '' pair`()
	{
		ErrorWhen("Unclosed '' pair","""
		var x
		set x 'z
		""")
	}
	@Test
	fun `FixedTest 1 | Invalid 10 | Unclosed "" pair`()
	{
		ErrorWhen("Unclosed \"\" pair","""
		msg " nope
		""")
	}
	@Test
	fun `FixedTest 1 | Invalid 11 | Nested procedures`()
	{
		ErrorWhen("Nested procedures","""
		proc a
		proc b
		end
		end
		""")
	}
	@Test
	fun `FixedTest 1 | Invalid 12 | Duplicate procedure names`()
	{
		ErrorWhen("Duplicate procedure names","""
		proc Q a
		end
		proc Q q
		end
		""")
	}
	@Test
	fun `FixedTest 1 | Invalid 13 | Duplicate parameter names`()
	{
		ErrorWhen("Duplicate parameter names","""
		proc Q q Q
		end
		""")
	}
	@Test
	fun `FixedTest 1 | Invalid 14 | End before beginning a block`()
	{
		ErrorWhen("End before beginning a block","""
		end
		msg " That\'s end"
		""")
	}
	@Test
	fun `FixedTest 1 | Invalid 15 | Unclosed blocks`()
	{
		ErrorWhen("Unclosed blocks","""
		var a
		set a 20
		ifeq a 19
			msg "eq"
		""")
	}
	@Test
	fun `FixedTest 1 | Invalid 16 | Undefined procedure`()
	{
		ErrorWhen("Undefined procedure","""
		var Yes
		caLL Say Yes
		""")
	}
	@Test
	fun `FixedTest 1 | Invalid 17 | The length of arguments does not match the length of parameters`()
	{
		ErrorWhen("The length of arguments does not match the length of parameters","""
		var P Q
		call What P Q
		proc What Is The Answer
			msg "42"
		end
		""")
	}
	@Test
	fun `FixedTest 1 | Invalid 18 | Recursive call`()
	{
		ErrorWhen("Recursive call","""
		var A
		set a 20
		call Wrap a
		proc Say x
			msg "It is "x
			call Wrap X
		end
		Proc Wrap X
			call Say x
		eNd
		""")
	}



	fun CHR(vararg Q : Int) = String(Q,0,Q.size)
	fun Clamp(Q : Int) : Int
	{
		val R = Q % 256
		return if (R < 0) 256 + R else R
	}
	val Edge = arrayOf(0,1,2,125,126,127,128,129,130,254,255)
	@Test
	fun `FixedTest 2 | Advanced 0 | Works for divmod`()
	{
		Edge.forEach{L -> Edge.forEach{R ->
			if (0 < R)
			{
				val Div = Clamp(L / R)
				val Mod = Clamp(L % R)

				Check("""
				vaR toString __proto__ hasOwnProperty ValueOf
				reAd __protO__
				rEad toStrinG
				diVMod __prOTO__ toStrinG hasOWNProperty valueOf
				msg TOsTrinG __proto__ HasOwnProperty valueOF
				""",CHR(L,R),CHR(R,L,Div,Mod),"$L `divmod` $R == ($Div,$Mod)")
				println("<PASSED::>Test Passed\n")
			}
		}}
	}
	@Test
	fun `FixedTest 2 | Advanced 1 | Works for cmp`()
	{
		Edge.forEach{L -> Edge.forEach{R ->
			val CMP = Clamp(if (L < R) -1 else if (R < L) 1 else 0)

			Check("""
			var __defineGetter__  hasOwnProperty __lookupGetter__ __lookupSetter__ propertyIsEnumerable constructor toString toLocaleString valueOf isPrototypeOf
			reAd __defineGetter__
			rEad constructor
			call __PROto__ constructor __defineGetter__
			msg constructor __defineGetter__ valueOf

			proc __proto__ __defineSetter__ constructor
				cmp __defineSetter__ constructor valueOf
			end
			""",CHR(R,L),CHR(L,R,CMP),"$L `cmp` $R == $CMP")
			println("<PASSED::>Test Passed\n")
		}}
	}
}
