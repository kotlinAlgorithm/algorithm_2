lateinit var matrixArr: Array<Pair<Int, Int>>
lateinit var dpArr: Array<IntArray>

fun main() {
    val n = readln().toInt()

    matrixArr = Array(n) { Pair(0, 0) }
    dpArr = Array(n) { IntArray(n) { Int.MAX_VALUE } }

    for (i in 0 until n) {
        val (row, column) = readln().split(" ").map { it.toInt() }
        matrixArr[i] = Pair(row, column)
    }

    if (n == 1) {
        println(0)
        return
    }
    for (i in 0 until n) {
        dpArr[i][i] = 0
    }
   
    for (size in 1 until n) {
        for (start in 0 until n - size) {
            for (division in start..start + size) {
                var rightSide = 0
                var leftSide = 0
                if (division == start + size) {
                    leftSide = dpArr[start][division]
                    rightSide = 0
                } else {
                    leftSide = dpArr[start][division]
                    rightSide = dpArr[division + 1][start + size]
                }
                dpArr[start][start + size] = minOf(
                    dpArr[start][start + size],
                    leftSide + rightSide + matrixArr[start].first * matrixArr[division].second * matrixArr[start + size].second
                )
            }
        }
    }

    println(dpArr[0][n - 1])

}