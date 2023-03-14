lateinit var gameMap: Array<Array<String>>
var answer = 0
var flg = false
var historyQ = mutableListOf<Pair<Int, Int>>()
fun main() {
    gameMap = Array(12) { readln().chunked(1).toTypedArray() }

    do {
        flg = false
        for (y in 0 until 12) {
            for (x in 0 until 6) {
                if (gameMap[y][x] != ".") {
                    dfs(y, x, gameMap[y][x], Array(12) { BooleanArray(6) { false } })
                    if (historyQ.size > 3) {
                        remove()//.으로 바꾸기
                        flg = true
                    }//4개부터 뿌요뿌요
                    historyQ.clear()
                }
            }
        }
        if (flg) {
            answer++
            gravity()//밑에서부터 다시 채우기
        }

    } while (flg)

    println(answer)
}

val dx = listOf(-1, 1, 0, 0)
val dy = listOf(0, 0, 1, -1)
fun dfs(y: Int, x: Int, target: String, visit: Array<BooleanArray>) {
    historyQ.add(Pair(y, x))
    visit[y][x] = true

    for (i in 0 until 4) {
        val ny = y + dy[i]
        val nx = x + dx[i]
        if (ny < 0 || nx < 0 || ny > 11 || nx > 5) {
            continue
        }
        if (gameMap[ny][nx] != target) {
            continue
        }
        if (visit[ny][nx]) {
            continue
        }
        dfs(ny, nx, target, visit)
    }
}

fun remove() {
    while (historyQ.isNotEmpty()) {
        val (y, x) = historyQ.removeLast()
        gameMap[y][x] = "."
    }
}

fun gravity() {
    for (x in 0 until 6) {
        for (y in 11 downTo 1) {
            if (gameMap[y][x] != ".") {
                continue
            }
            for (k in y - 1 downTo 0) {
                if (gameMap[k][x] != ".") {
                    gameMap[y][x] = gameMap[k][x]
                    gameMap[k][x] = "."
                    break
                }
            }//바꾸기
        }
    }
}
