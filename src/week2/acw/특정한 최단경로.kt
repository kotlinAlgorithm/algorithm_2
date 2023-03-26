import java.util.*

val br = System.`in`.bufferedReader()

lateinit var graph: Array<ArrayList<Pair<Int, Int>>>
lateinit var dist: IntArray
val q = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
const val INF = Int.MAX_VALUE

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    graph = Array(n + 1) { arrayListOf() }
    dist = IntArray(n + 1) { INF }
    
    repeat(m) {
        val (s, e, c) = br.readLine().split(" ").map { it.toInt() }
        graph[s].add(Pair(e, c))
        graph[e].add(Pair(s, c))
    }
    val (n1, n2) = br.readLine().split(" ").map { it.toInt() }
    val dijkstraStartList = listOf(1,n1,n2)
    dijkstra(dijkstraStartList[0])

    var case1 = 0 //1번노드를 먼저방문
    var case2 = 0// 2번노드를 먼저방문

    if (dist[n1] == INF && dist[n2] == INF) {
        println(-1)
        return
    } else {
        case1 = dist[n1]
        case2 = dist[n2]
    }

    dist = IntArray(n + 1) { INF }
    q.clear()
    dijkstra(dijkstraStartList[1])

    case1=case1.savePlus(dist[n2])
    case2=case2.savePlus(dist[n])

    dist = IntArray(n + 1) { INF }
    q.clear()
    dijkstra(dijkstraStartList[2])

    case2=case2.savePlus(dist[n1])
    case1=case1.savePlus(dist[n])


    if(case1>=INF && case2>=INF){
        println(-1)
    }else{
        println(minOf(case1, case2))
    }
    br.close()
}
fun Int.savePlus(num:Int):Int{
    return if(this==INF||num==INF){
        INF
    }else{
        this+num
    }
}
fun dijkstra(start: Int) {
    q.add(Pair(start, 0))
    dist[start] = 0

    while (q.isNotEmpty()) {
        val (nodeNum, cost) = q.poll()
        if (dist[nodeNum] < cost) {
            continue
        }
        for (i in graph[nodeNum]) {
            val nc = cost + i.second
            if (dist[i.first] > nc) {
                dist[i.first] = nc
                q.add(Pair(i.first, nc))
            }
        }
    }

}
