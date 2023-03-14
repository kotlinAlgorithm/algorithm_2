package s2_week1

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/132266
 * BFS
 */

import java.util.LinkedList

class `부대복귀` {

    data class State(val node: Int, val accDistance: Int)

    private lateinit var graph: Array<MutableList<Int>>
    private lateinit var distFromDest: IntArray

    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        graph = Array(n + 1) { mutableListOf<Int>() }
        distFromDest = IntArray(n + 1) { -1 }

        roads.forEach { road ->
            val node1 = road[0]
            val node2 = road[1]
            graph[node1].add(node2)
            graph[node2].add(node1)
        }

        bfsFromDestination(destination)
        return sources.map { node -> distFromDest[node] }.toIntArray()
    }

    private fun bfsFromDestination(destination: Int) {
        val q = LinkedList<State>()
        q.offer(State(destination, 0))
        distFromDest[destination] = 0

        while (q.isNotEmpty()) {
            val (node, accDistance) = q.poll()
            if (accDistance > distFromDest[node]) {
                continue
            }

            for (nextNode in graph[node]) {
                val nextDistance = accDistance + 1
                if (distFromDest[nextNode] == -1 || nextDistance < distFromDest[nextNode]) {
                    q.offer(State(nextNode, nextDistance))
                    distFromDest[nextNode] = nextDistance
                }
            }
        }
    }
}