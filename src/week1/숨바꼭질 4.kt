package s2_week1

import java.util.LinkedList

private val prevPoint = IntArray(200_001) { - 1 }

fun main() {
    val (subin, sister) = readln().split(" ").map(String::toInt)
    findSister(subin, sister)
    val moveRoute = getSubinMoveRoute(subin, sister)
    println(moveRoute.size - 1)
    print(moveRoute.joinToString(" "))
}

private fun getSubinMoveRoute(subin: Int, sister: Int): List<Int> {
    val route = LinkedList<Int>()
    var currentPoint = sister

    while (currentPoint != subin) {
        route.addFirst(currentPoint)
        currentPoint = prevPoint[currentPoint]
    }
    route.addFirst(subin)
    return route
}

private fun findSister(subin: Int, sister: Int) {
    val q = LinkedList<Int>()
    q.offer(subin)
    prevPoint[subin] = subin

    while (q.isNotEmpty()) {
        val point = q.poll()

        for(i in 0 until 3) {
            val nextPoint = when (i) {
                0 -> point + 1
                1 -> point - 1
                else -> 2 * point
            }

            if (nextPoint !in prevPoint.indices || prevPoint[nextPoint] != -1) {
                continue
            }
            prevPoint[nextPoint] = point
            if (nextPoint == sister) {
                return
            }
            q.offer(nextPoint)
        }
    }
}