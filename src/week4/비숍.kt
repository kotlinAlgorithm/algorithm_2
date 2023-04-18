import kotlin.math.max

lateinit var chessMap: Array<IntArray>
val chessMapSize by lazy {
    chessMap.size
}
var answer = 0

fun placeBishop(y: Int, x: Int, possibleCheckMap: Array<IntArray>) {
    chessMap[y][x] = 2

    var targetY = y + 1
    var targetX = x - 1
    while (targetY < chessMapSize && targetX > -1) {
        possibleCheckMap[targetY][targetX]++
        targetY++
        targetX--
    }

    targetY = y + 1
    targetX = x + 1
    while (targetX < chessMapSize && targetY < chessMapSize) {
        possibleCheckMap[targetY][targetX]++
        targetY++
        targetX++
    }
}

fun deleteBishop(y: Int, x: Int, possibleCheckMap: Array<IntArray>) {
    chessMap[y][x] = 1

    var targetY = y + 1
    var targetX = x - 1
    while (targetY < chessMapSize && targetX > -1) {
        possibleCheckMap[targetY][targetX]--
        targetY++
        targetX--
    }

    targetY = y + 1
    targetX = x + 1
    while (targetX < chessMapSize && targetY < chessMapSize) {
        possibleCheckMap[targetY][targetX]--
        targetY++
        targetX++
    }
}

fun dfsFind(y: Int, x: Int, cnt: Int, possibleCheckMap: Array<IntArray>) {
    if (y >= chessMapSize) {
        answer = max(answer, cnt)
        return
    }

    if (chessMap[y][x] == 1 && possibleCheckMap[y][x] < 1) {
        placeBishop(y, x, possibleCheckMap)
        val nextY = if (x == chessMapSize - 1) y + 1 else y
        val nextX = if (x == chessMapSize - 1) 0 else x + 1
        dfsFind(nextY, nextX, cnt + 1, possibleCheckMap)
        deleteBishop(y, x, possibleCheckMap)
    }

    val nextY = if (x == chessMapSize - 1) y + 1 else y
    val nextX = if (x == chessMapSize - 1) 0 else x + 1
    dfsFind(nextY, nextX, cnt, possibleCheckMap)
}

fun main() {
    val n = readln().toInt()
    chessMap = Array(n) { readln().trim().split(" ").map { it.toInt() }.toIntArray() }
    val possibleCheckMap = Array(n) { IntArray(n) { 0 } }

    dfsFind(0, 0, 0, possibleCheckMap)
    println(answer)
}