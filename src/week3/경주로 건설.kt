package src.week3

import java.util.LinkedList

data class State(val row: Int, val col: Int, val dir: Int, val cost: Int)
// dir) 0:상, 1:하, 2:좌, 3:우

class `Solution경주로 건설` {

    private lateinit var minCost: Array<Array<IntArray>>

    fun solution(board: Array<IntArray>): Int {
        minCost = Array(board.size) {
            Array(board.size) {
                IntArray(4) { Int.MAX_VALUE }
            }
        }

        val dRow = intArrayOf(-1 , 1, 0, 0)
        val dCol = intArrayOf(0, 0, -1, 1)

        val q = LinkedList<State>()
        q.offer(State(0, 0, 1, 0))
        q.offer(State(0, 0, 3, 0))
        for (i in 0 until 4) {
            minCost[0][0][i] = 0
        }

        while (q.isNotEmpty()) {
            val (row, col, dir, cost) = q.poll()
            if (cost > minCost[row][col][dir]) {
                continue
            }
            if (row == board.lastIndex && col == board.lastIndex) {
                continue
            }

            for (nextDir in 0 until 4) {
                val nextRow = row + dRow[nextDir]
                val nextCol = col + dCol[nextDir]
                if (nextRow !in board.indices || nextCol !in board.indices) continue
                if (board[nextRow][nextCol] == 1) continue

                val nextCost = cost + calculateCost(dir, nextDir)

                if (nextCost < minCost[nextRow][nextCol][nextDir]) {
                    q.offer(State(nextRow, nextCol, nextDir, nextCost))
                    minCost[nextRow][nextCol][nextDir] = nextCost
                }
            }
        }

        return minCost[board.lastIndex][board.lastIndex].minOrNull()!!
    }

    private fun calculateCost(prevDir: Int, newDir: Int): Int =
        when (newDir) {
            0, 1 -> if (prevDir == 2 || prevDir == 3) 600 else 100
            else -> if (prevDir == 0 || prevDir == 1) 600 else 100
        }
}