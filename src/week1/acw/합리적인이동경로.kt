import java.util.*

/*
    정점으로 갔을 떄 정점까지의 거리보다 정점에서 목표까지의 거리가 짧으면 합리적인 경로
 */
lateinit var dist:IntArray
lateinit var distToEnd:IntArray
lateinit var graph:Array<ArrayList<Pair<Int,Int>>>
val q= PriorityQueue<Pair<Int,Int>>(compareBy{it.second})
var answer=0

fun main(){
    val (n,m)= readln().split(" ").map{it.toInt()}
    graph=Array(n+1){arrayListOf()}
    dist=IntArray(n+1){Int.MAX_VALUE}
    distToEnd=IntArray(n+1){Int.MAX_VALUE}
    distToEnd[2]=0
    repeat(m){
        val(s,e,c)= readln().split(" ").map { it.toInt() }
        graph[s].add(Pair(e,c))
        graph[e].add(Pair(s,c))
        if(s==2){
            distToEnd[e]=c
        }else if(e==2){
            distToEnd[s]=c
        }
    }
    dijkstra(1)
    println(answer)

}
fun dijkstra(start:Int){
    q.add(Pair(start,0))
    dist[start]=0

    while(q.isNotEmpty()){
        val now=q.poll()

        if(dist[now.first]<now.second){
            continue
        }

        for(i in graph[now.first]){
            val cost=now.second+i.second
            if(i.first==2){
                answer++
            }
            if(dist[i.first]>cost && (distToEnd[i.first]<=distToEnd[now.first] ||i.first==2)){
                dist[i.first]=cost
                if(i.first==2){
                    distToEnd[i.first]=cost
                }
                q.add(Pair(i.first,cost))
            }
        }


    }
}