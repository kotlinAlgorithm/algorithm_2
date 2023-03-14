import java.util.*

data class History(
    val time: Int,
    val position: Int
)
val parents = IntArray(200001) { it }


fun main() {
    val (subin, sister) = readln().split(" ").map { it.toInt() }

    val queue = LinkedList<History>()
    queue.offer(History(0, subin))



    while (queue.isNotEmpty()) {
        val (time, position) = queue.poll()
        if (position == sister) {
            println(time)
            println((path(position,subin)).reversed().joinToString(" "))
            return
        }

        val nextPositions = if(position<sister){
                listOf(position-1,position + 1, position * 2)
                .filter { it in 0..200000 && parents[it]==it }
            }else{
                listOf(position-1).filter{parents[it]==it}
            }

        nextPositions.forEach { nextPosition ->
            queue.offer(History(time + 1, nextPosition))
            parents[nextPosition]=position
        }
    }
}
fun path(now: Int, subin: Int): List<Int> {
    val path = mutableListOf<Int>()
    var current = now
    while (current != subin) {
        path.add(current)
        current = parents[current]
    }
    path.add(subin)
    return path
}