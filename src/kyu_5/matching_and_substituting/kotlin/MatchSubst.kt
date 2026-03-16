// https://www.codewars.com/kata/59de1e2fe50813a046000124/train/kotlin

package kyu_5.matching_and_substituting.kotlin

object MatchSubst {

    private val versionRegex = Regex("^\\d+\\.\\d+\$")
    private val phoneRegex = Regex("\\+1-\\d{3}-\\d{3}-\\d{4}")

    fun change(s: String, prog: String, version: String): String {
        val lines = s.split("\n")
        val data = lines
            .map { it.split(": ", limit = 2) }
            .associate { (key, value) -> key to value }

        val inputVersion = data["Version"].orEmpty()
        val inputPhone = data["Phone"].orEmpty()

        if (!inputVersion.matches(versionRegex) || !inputPhone.matches(phoneRegex)) return "ERROR: VERSION or PHONE"

        val resultVersion = if (inputVersion == "2.0") "2.0" else version

        return "Program: $prog Author: g964 Phone: +1-503-555-0090 Date: 2019-01-01 Version: $resultVersion"
    }
}