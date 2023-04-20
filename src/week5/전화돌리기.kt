package src.week5

import java.util.StringTokenizer

private lateinit var relations: Array<MutableList<Int>>
private lateinit var hasCycle: Array<Boolean?>
private lateinit var visited: BooleanArray

fun main() {
    val (personCount, relationCount) = readln().split(" ").map(String::toInt)
    relations = Array(personCount + 1) { mutableListOf() }
    hasCycle = Array(personCount + 1) { null }
    visited = BooleanArray(personCount + 1)

    repeat(relationCount) {
        val st = StringTokenizer(readln())
        relations[st.nextToken().toInt()].add(st.nextToken().toInt())
    }

    for (person in 1..personCount) {
        if (visited[person]) continue
        doesMakeCycle(person)
    }
    hasCycle.count { it == false }.let { print(it) }
}

private fun doesMakeCycle(person: Int): Boolean {
    if (hasCycle[person] != null) {
        return hasCycle[person]!!
    }
    if (visited[person]) {
        hasCycle[person] = true
        return true
    }
    visited[person] = true

    var doesThisMakeCycle = false
    for (nextPerson in relations[person]) {
        if(doesMakeCycle(nextPerson)) {
            doesThisMakeCycle = true
        }
    }

    hasCycle[person] = doesThisMakeCycle
    return doesThisMakeCycle
}