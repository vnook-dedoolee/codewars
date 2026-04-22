// https://www.codewars.com/kata/5c80b55e95eba7650dc671ea/train/kotlin

package kyu_5.complete_binary_tree.kotlin

fun completeBinaryTree(arr: IntArray): IntArray {
    val n = arr.size
    val result = IntArray(n)
    val stack = mutableListOf<Int>()
    var nodeIndex = 0
    var arrIndex = 0

    while (nodeIndex < n || stack.isNotEmpty()) {
        while (nodeIndex < n) {
            stack.add(nodeIndex)
            nodeIndex = 2 * nodeIndex + 1
        }
        nodeIndex = stack.removeAt(stack.lastIndex)
        result[nodeIndex] = arr[arrIndex++]
        nodeIndex = 2 * nodeIndex + 2
    }

    return result
}