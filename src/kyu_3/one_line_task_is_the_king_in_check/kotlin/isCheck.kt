// https://www.codewars.com/kata/5e320fe3358578001e04ad55/train/kotlin

package kyu_3.one_line_task_is_the_king_in_check.kotlin

fun isCheck(c: Array<Array<String>>)=(0..3).map{it to StringBuilder("$it")}.any{ (i,b)->Regex("([01].*♟.{10}|[♝♛].{10}( .{10})*|[♜♛] *|[♜♛](.{9} )*.{9}|♞(.{11}|.{20}))♔")in(0..7).map{ y->(0..7).map{b.append(c[if(i and 2>0)7-y else y][if(i%2>0)it else 7-it])}[0].append("99")}[0]}