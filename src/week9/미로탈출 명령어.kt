package src.week9

import java.util.LinkedList

class `미로탈출 명령어` {

    private val dRow = intArrayOf(1, 0, 0, -1)
    private val dCol = intArrayOf(0, -1, 1, 0)
    private val oppositeDir = intArrayOf(3, 2, 1, 0)

    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        return getBestPath(n, m, x - 1, y - 1, r - 1, c - 1, k)
    }

    data class Path(val row: Int, val col: Int, val step: Int, val dir: Int)

    private fun getBestPath(rowSize: Int, colSize: Int, sRow: Int, sCol: Int, eRow: Int, eCol: Int, distance: Int): String {
        val q = LinkedList<Path>()
        val arriveDir = Array(rowSize) { Array(colSize) { IntArray(distance + 1) { -1 } } }

        for (dir in 0 until 4) {
            q.offer(Path(sRow, sCol, 0, dir))
        }

        while (q.isNotEmpty()) {
            val (row, col, step, dir) = q.poll()

            for (dir in 0 until 4) {
                val nextRow = row + dRow[dir]
                val nextCol = col + dCol[dir]

                if (nextRow !in 0 until rowSize || nextCol !in 0 until colSize) {
                    continue
                }
                if (arriveDir[nextRow][nextCol][step + 1] != -1) continue

                arriveDir[nextRow][nextCol][step + 1] = dir
                if (step + 1 < distance) {
                    q.offer(Path(nextRow, nextCol, step + 1, dir))
                }
            }
        }

        return if (arriveDir[eRow][eCol][distance] == -1) {
            "impossible"
        } else {
            buildPath(arriveDir, eRow, eCol, distance)
        }
    }

    private fun buildPath(arriveDir: Array<Array<IntArray>>, row: Int, col: Int, step: Int): String {
        if (step == 0) return ""

        val dir = arriveDir[row][col][step]
        val prevRow = row + dRow[oppositeDir[dir]]
        val prevCol = col + dCol[oppositeDir[dir]]
        val alphabet = when (dir) {
            0 -> 'd'
            1 -> 'l'
            2 -> 'r'
            else -> 'u'
        }

        return "${buildPath(arriveDir, prevRow, prevCol, step - 1)}$alphabet"
    }
}