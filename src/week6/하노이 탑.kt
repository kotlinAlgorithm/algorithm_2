package src.week6

import java.lang.StringBuilder
import java.math.BigInteger

private var circleCount = 0
private var moveCount = 0
private lateinit var moveCountTable: Array<BigInteger>
private val moveProcess = StringBuilder()

fun main() {
    circleCount = readln().toInt()
    if (circleCount <= 20) {
        moveCircles(1, 2, 3, circleCount)
        println(moveCount)
        if (circleCount <= 20) {
            print(moveProcess.toString())
        }
    } else {
        moveCountTable = Array(circleCount + 1) { BigInteger.ZERO }
        moveCountTable[1] = BigInteger.ONE
        countCircleMovement(circleCount).let { print(it) }
    }
}

private fun moveCircles(fromPoll: Int, midPoll: Int, toPoll: Int, circleCount: Int) {
    if (circleCount == 1) {
        moveTopCircle(fromPoll, toPoll)
        return
    }

    moveCircles(fromPoll = fromPoll, midPoll = toPoll, toPoll = midPoll, circleCount - 1)
    moveTopCircle(fromPoll, toPoll)
    moveCircles(fromPoll = midPoll, midPoll = fromPoll, toPoll = toPoll, circleCount - 1)
}

private fun moveTopCircle(fromPoll: Int, toPoll: Int) {
    moveCount++
    if (circleCount <= 20) {
        moveProcess.append("$fromPoll $toPoll\n")
    }
}

private fun countCircleMovement(circleCount: Int): BigInteger {
    // base 사례인 circleCount == 1인 경우까지 포함된다
    if (moveCountTable[circleCount] != BigInteger.ZERO) {
        return moveCountTable[circleCount]
    }

    moveCountTable[circleCount] = countCircleMovement(circleCount - 1) + BigInteger.ONE + countCircleMovement(circleCount - 1)
    return moveCountTable[circleCount]
}