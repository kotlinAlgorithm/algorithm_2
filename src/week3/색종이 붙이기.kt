package src.week3

private lateinit var graph: Array<IntArray>
private var initialOneCount = 0
private val paperCount = intArrayOf(0, 5, 5, 5, 5, 5)
private var minUsedPaperCount = Int.MAX_VALUE

fun main() {
    graph = Array(10) {
        readln().split(" ").map(String::toInt).toIntArray().also {
            initialOneCount += it.count { blockValue -> blockValue == 1 }
        }
    }

    tryAllCase(0, 0, 0, 0)

    if (minUsedPaperCount == Int.MAX_VALUE) {
        print(-1)
    } else {
        print(minUsedPaperCount)
    }
}

private fun tryAllCase(startRow: Int, startColumn: Int, usedPaperCount: Int, coveredCount: Int) {
    if (coveredCount == initialOneCount) {
        minUsedPaperCount = minOf(minUsedPaperCount, usedPaperCount)
        return
    }
    if (startRow == 10 || startColumn == 10) {
        return
    }
    if (usedPaperCount == 25) {
        return
    }

    val nextRow = if (startColumn == 9) startRow + 1 else startRow
    val nextColumn = if (startColumn == 9) 0 else startColumn + 1

    if (graph[startRow][startColumn] == 1) {
        for (size in 1..5) {
            if (paperCount[size] == 0) continue
            if (startRow + size > 10 || startColumn + size > 10) break

            if (canOverlay(size, startRow, startColumn)) {
                overlay(size, startRow, startColumn)
                paperCount[size] -= 1
                tryAllCase(nextRow, nextColumn, usedPaperCount + 1, coveredCount + size * size)
                paperCount[size] += 1
                undo(size, startRow, startColumn)
            }
        }
    } else {
        tryAllCase(nextRow, nextColumn, usedPaperCount, coveredCount)
    }
}

private fun overlay(squareSize: Int, startRow: Int, startColumn: Int) {
    for (row in startRow until startRow + squareSize) {
        for (column in startColumn until startColumn + squareSize) {
            graph[row][column] = 0
        }
    }
}

private fun undo(squareSize: Int, startRow: Int, startColumn: Int) {
    for (row in startRow until startRow + squareSize) {
        for (column in startColumn until startColumn + squareSize) {
            graph[row][column] = 1
        }
    }
}

private fun canOverlay(squareSize: Int, startRow: Int, startColumn: Int): Boolean {
    for (row in startRow until startRow + squareSize) {
        for (column in startColumn until startColumn + squareSize) {
            if (graph[row][column] == 0) {
                return false
            }
        }
    }
    return true
}