import kotlin.math.pow

val dragonCurbMap = Array(101) { BooleanArray(101) { false } }
val dy = listOf(0, -1, 0, 1)
val dx = listOf(1, 0, -1, 0)

fun main() {
    val n = readln().toInt()
    repeat(n) {
        val input = readln().split(" ").map { it.toInt() }
        makeDragonCurb(input[0], input[1], input[2], input[3])
    }
    println(checkDragonSquare())
}

fun makeDragonCurb(x: Int, y: Int, dir: Int, targetGen: Int) {
    val len = 2.0.pow(targetGen).toInt()
    val dragonDirArr = IntArray(len) { 0 }
    dragonDirArr[0] = dir

    var endNow = 0
    var idxNow = 1
    for (gen in 1..targetGen) {
        endNow = 2.0.pow(gen - 1).toInt() - 1
        for (i in endNow downTo 0) {
            dragonDirArr[idxNow++] = (dragonDirArr[i] + 1) % 4
        }
    }//dragon curb 방향 설정.

    drawDragonCurb(x, y, dragonDirArr)


}

fun drawDragonCurb(x: Int, y: Int, gen: IntArray) {
    var yNow = y
    var xNow = x
    dragonCurbMap[y][x] = true

    for (i in gen.indices) {
        val ny = yNow + dy[gen[i]]
        val nx = xNow + dx[gen[i]]

        dragonCurbMap[ny][nx] = true
        yNow = ny
        xNow = nx
    }
}

fun checkDragonSquare(): Int {
    var answer = 0
    for (i in 0..99) {
        for (j in 0..99) {
            if (dragonCurbMap[i][j] && dragonCurbMap[i + 1][j] && dragonCurbMap[i][j + 1] && dragonCurbMap[i + 1][j + 1]) {
                answer++
            }
        }
    }
    return answer
}