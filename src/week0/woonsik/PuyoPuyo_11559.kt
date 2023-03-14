import Color.Companion.isColor
import Color.Companion.isSame
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/*
......
......
......
......
......
......
......
......
.Y....
.YG...
RRYG..
RRYGG.
 */

private data class Dot(
    val y: Int,
    val x: Int,
)

private enum class Color(val code: String) {
    R("R"),
    G("G"),
    B("B"),
    P("P"),
    Y("Y"),
    D(".");

    companion object {
        fun find(code: String): Color {
            return Color.values().first { it.code == code }
        }

        fun Color.isColor(): Boolean {
            return this != D
        }

        fun Color.isSame(color: Color): Boolean {
            return this == color
        }
    }
}

class PuyoPuyo_11559 {
    val dy = arrayOf(-1, 1, 0, 0)
    val dx = arrayOf(0, 0, -1, 1)
    private val map = Array(14) { Array(8) { Color.D } }
    val br = BufferedReader(InputStreamReader(System.`in`))
    private var bombCount = 0
    private val bombQueues = LinkedList<Dot>()
    private var visited = Array(14) { BooleanArray(8) { false } }

    fun solution() {
        initMap()
        game()
        print(bombCount)
    }

    private fun game() {
        while (true) {
            findBombColor(map)
            if (bombQueues.isEmpty()) return
            bombColor()
            bombCount++
        }
    }

    private fun findBombColor(map: Array<Array<Color>>) {
        for (i in 12 downTo 1) {
            for (j in 6 downTo 1) {
                if (!visited[i][j] && map[i][j].isColor()) {
                    bombBfs(Dot(i, j))
                }
            }
        }
    }

    private fun bombBfs(dot: Dot) {
        val origin = map[dot.y][dot.x]
        val queue = LinkedList<Dot>()
        val bombQueue = LinkedList<Dot>()
        queue.add(dot)

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for (dir in 0..3) {
                val ny = cur.y + dy[dir]
                val nx = cur.x + dx[dir]

                if (ny >= 13 || ny <= 0 || nx >= 7 || nx <= 0) continue
                if (visited[ny][nx]) continue
                if (!map[ny][nx].isColor()) continue
                if (!map[ny][nx].isSame(origin)) continue

                visited[ny][nx] = true
                val nextDot = Dot(ny, nx)
                queue.add(nextDot)
                bombQueue.add(nextDot)
            }
        }
        if (bombQueue.size >= 4) {
            bombQueues.addAll(bombQueue)
            bombQueues.add(dot)
        }
    }

    private fun bombColor() {
        visited = Array(14) { BooleanArray(8) { false } }
        val downColumnSet = mutableSetOf<Int>()
        while (bombQueues.isNotEmpty()) {
            val (y, x) = bombQueues.poll()
            map[y][x] = Color.D
            downColumnSet.add(x)
        }
        downColumnSet.map { col -> gravity(col) }
    }

    private fun gravity(col: Int) {
        var startRow = 12
        while (true) {
            val colorRow = findUpColorRow(startRow, col)
            val notColorRow = findUpNotColorRow(startRow, col)
            if (colorRow == -1) return
            if (notColorRow == -1) return
            if (notColorRow < colorRow) return
            if (map[colorRow][col].isColor()) {
                if (colorRow != notColorRow) {
                    map[notColorRow][col] = map[colorRow][col]
                    map[colorRow][col] = Color.D
                    startRow = notColorRow - 1
                }
            }
        }
    }

    private fun findUpNotColorRow(r: Int, col: Int): Int {
        var row = r
        while (row >= 1) {
            if (!map[row][col].isColor()) return row
            row--
        }
        return -1
    }

    private fun findUpColorRow(r: Int, col: Int): Int {
        var row = r
        while (row >= 1) {
            if (map[row][col].isColor()) return row
            row--
        }
        return -1
    }

    private fun initMap() {
        for (i in 1..12) {
            val line = br.readLine().toCharArray()
            for (j in 1..6) {
                map[i][j] = Color.find(line[j - 1].toString())
            }
        }
    }


}

fun main() {
    val problem = PuyoPuyo_11559()
    problem.solution()
}