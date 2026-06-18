// https://www.codewars.com/kata/587f0abdd8730aafd4000035/train/kotlin

package kyu_6.sha_256_cracker.kotlin

import java.security.MessageDigest

fun crackSha256(hash: String, chars: String): String? {
    val arr = chars.toCharArray()
    val digest = MessageDigest.getInstance("SHA-256")

    return permuteAndCheck(arr, 0, hash, digest)
}

private fun permuteAndCheck(
    arr: CharArray,
    start: Int,
    targetHash: String,
    digest: MessageDigest
): String? {
    if (start == arr.size) {
        val word = String(arr)
        val hash = digest.digest(word.toByteArray())
            .joinToString("") { "%02x".format(it) }
        return if (hash == targetHash) word else null
    }

    for (i in start until arr.size) {
        swap(arr, start, i)

        val result = permuteAndCheck(arr, start + 1, targetHash, digest)
        if (result != null) return result

        swap(arr, start, i)
    }

    return null
}

private fun swap(arr: CharArray, i: Int, j: Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}

private fun String.sha256(): String {
    val digest = MessageDigest.getInstance("SHA-256")
    return digest.digest(this.toByteArray())
        .joinToString("") { "%02x".format(it) }
}