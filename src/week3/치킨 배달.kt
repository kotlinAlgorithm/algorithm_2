package src.week3

/**
 * https://www.acmicpc.net/problem/15686
 * 완전 탐색(백트래킹)
 * 문제 해석하고 풀이하는 과정이 조금 어지러웠다
 */
import kotlin.math.abs

data class Location(val row: Int, val col: Int) {
    companion object {
        val garbage = Location(-1, -1)
    }
}

private lateinit var selectedChickens: Array<Location>
private var allChickens = mutableListOf<Location>()
private var houses = mutableListOf<Location>()
private var minTotalChickenDistance = Int.MAX_VALUE

fun main() {
    val (graphSize, selectionCount) = readln().split(" ").map(String::toInt)
    selectedChickens = Array(selectionCount) { Location.garbage }
    repeat(graphSize) { row ->
        readln().split(" ").forEachIndexed { col, str ->
            if (str == "1") {
                houses.add(Location(row, col))
            } else if (str == "2") {
                allChickens.add(Location(row, col))
            }
        }
    }

    selectChicken(0, 0)
    print(minTotalChickenDistance)
}

fun selectChicken(selectionIndex: Int, candidateIndex: Int) {
    if (selectedChickens.size - selectionIndex > allChickens.size - candidateIndex) {
        return
    }
    if (selectionIndex == selectedChickens.size) {
        minTotalChickenDistance = minOf(minTotalChickenDistance, calculateTotalChickenDistance())
        return
    }

    selectedChickens[selectionIndex] = allChickens[candidateIndex]
    selectChicken(selectionIndex + 1, candidateIndex + 1)

    selectChicken(selectionIndex, candidateIndex + 1)
}

fun calculateTotalChickenDistance(): Int {
    var totalChickenDistance = 0
    for (house in houses) {
        var chickenDistance = Int.MAX_VALUE
        for (bbq in selectedChickens) {
            chickenDistance = minOf(chickenDistance, abs(house.row - bbq.row) + abs(house.col - bbq.col))
        }
        totalChickenDistance += chickenDistance
    }
    return totalChickenDistance
}