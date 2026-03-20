// https://www-codewars-com/kata/582e0450fe38013dbc0002d3/train/kotlin

package kyu_6.regex_tic_tac_toe_win_checker.kotlin

fun winChecker(board : String) : Boolean {
    return Regex("(\\w)(..(\\1|.\\1.)..\\1|.\\1.\\1..$|\\1\\1(...)*$)").find(board) != null
}




