package src.week4

/**
 * https://www.acmicpc.net/problem/14502
 * 구현 + 완전 탐색, 절차가 많아서
 */
enum class Area {
    SPACE, WALL, VIRUS
}

private fun String.toArea(): Area =
    when(this) {
        "0" -> Area.SPACE
        "1" -> Area.WALL
        else -> Area.VIRUS
    }

private val dRow = intArrayOf(-1, 1, 0, 0)
private val dCol = intArrayOf(0, 0, -1, 1)

private var maxSpaceCount = Int.MIN_VALUE
private lateinit var initialLab: Array<Array<Area>>
private lateinit var lab: Array<Array<Area>>
private lateinit var visited: Array<BooleanArray>
private var totalIndexCount = 0

fun main() {
    val (rowSize, colSize) = readln().split(" ").map(String::toInt)
    initialLab = Array(rowSize) {
        readln().split(" ").map(String::toArea).toTypedArray()
    }
    lab = Array(rowSize) { row ->
        Array(colSize) { col ->
            initialLab[row][col]
        }
    }
    visited = Array(rowSize) { BooleanArray(colSize) }
    totalIndexCount = rowSize * colSize

    setWall(0, 0)
    print(maxSpaceCount)
}

private fun setWall(index: Int, wallCount: Int) {
    if (wallCount == 3) {
        spreadVirusOverLab()
        maxSpaceCount = maxOf(maxSpaceCount, lab.countSpace())
        lab.reset()
        visited.reset()
        return
    }
    if (index == totalIndexCount) {
        return
    }

    val row = index / lab[0].size
    val col = index % lab[0].size

    if (lab[row][col] == Area.SPACE) {
        lab[row][col] = Area.WALL
        initialLab[row][col] = Area.WALL
        setWall(index + 1, wallCount + 1)
        lab[row][col] = Area.SPACE
        initialLab[row][col] = Area.SPACE
        setWall(index + 1, wallCount)
    } else {
        setWall(index + 1, wallCount)
    }
}

private fun spreadVirusOverLab() {
    for (row in lab.indices) {
        for (col in lab[0].indices) {
            if (lab[row][col] == Area.VIRUS && !visited[row][col]) {
                visited[row][col] = true
                spreadVirus(row, col)
            }
        }
    }
}

private fun spreadVirus(row: Int, col: Int) {
    for (i in 0 until 4) {
        val nextRow = row + dRow[i]
        val nextCol = col + dCol[i]
        if (nextRow !in lab.indices || nextCol !in lab[0].indices) {
            continue
        }
        if (lab[nextRow][nextCol] == Area.SPACE && !visited[nextRow][nextCol]) {
            lab[nextRow][nextCol] = Area.VIRUS
            visited[row][col] = true
            spreadVirus(nextRow, nextCol)
        }
    }
}

private fun Array<Array<Area>>.countSpace(): Int {
    var count = 0
    for (row in this.indices) {
        for (col in this[0].indices) {
            if (this[row][col] == Area.SPACE) {
                count++
            }
        }
    }
    return count
}

private fun Array<Array<Area>>.reset() {
    for (row in this.indices) {
        for (col in this[0].indices) {
            this[row][col] = initialLab[row][col]
        }
    }
}

private fun Array<BooleanArray>.reset() {
    for (row in this.indices) {
        for (col in this[0].indices) {
            this[row][col] = false
        }
    }
}