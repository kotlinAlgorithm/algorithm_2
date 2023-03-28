package src.week3

private lateinit var graph: Array<IntArray>
private val paperCount = intArrayOf(0, 5, 5, 5, 5, 5)
private var minUsedPaperCount = Int.MAX_VALUE

fun main() {
    graph = Array(10) {
        readln().split(" ").map(String::toInt).toIntArray()
    }

    tryAllCase(0, 0)

    if (minUsedPaperCount == Int.MAX_VALUE) {
        print(-1)
    } else {
        print(minUsedPaperCount)
    }
}

private fun tryAllCase(startRow: Int, usedPaperCount: Int) {
    var foundRow = 0
    var foundColumn = -1

    for (row in startRow until 10) {
        for (column in 0 until 10) {
            if (graph[row][column] == 1) {
                foundRow = row
                foundColumn = column
                break
            }
        }
        if (foundColumn != -1) break
    }

    if (foundColumn == -1) {
        minUsedPaperCount = minOf(minUsedPaperCount, usedPaperCount)
        return
    }
    if (usedPaperCount == 25 || usedPaperCount >= minUsedPaperCount) {
        return
    }

    for (size in 1..5) {
        if (paperCount[size] == 0) continue
        if (canOverlay(size, foundRow, foundColumn)) {
            overlay(size, foundRow, foundColumn)
            paperCount[size] -= 1
            tryAllCase(foundRow, usedPaperCount + 1)
            paperCount[size] += 1
            undo(size, foundRow, foundColumn)
        }
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
    if (startRow + squareSize > 10 || startColumn + squareSize > 10) {
        return false
    }
    for (row in startRow until startRow + squareSize) {
        for (column in startColumn until startColumn + squareSize) {
            if (graph[row][column] == 0) {
                return false
            }
        }
    }
    return true
}