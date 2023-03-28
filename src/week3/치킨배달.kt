import java.lang.Integer.min
import java.lang.Math.abs
import java.util.*
import kotlin.collections.ArrayList

lateinit var city:Array<Array<Int>>
lateinit var dist:Array<Array<Array<Int>>>
var visited=Array<Boolean>(13){false}
var chickenStore=Array(13){Pair(0,0)}
lateinit var comb:MutableList<List<Int>>
var storeCount=0


fun calDist(cityX:Int,cityY:Int,storeX:Int,storeY:Int):Int{
    return abs(cityX-storeX)+abs(cityY-storeY)
}

fun combination(start:Int,len:Int)
{
    if(len==0){
        var li= mutableListOf<Int>()
        for(i in 0 until storeCount){
            if(visited[i]){
                li.add(i)
            }
        }
        comb.add(li)

        return
    }

    for(i in start until storeCount){

        visited[i]=true
        combination(i+1,len-1)
        visited[i]=false
    }

}


fun main(){
    val (N,M)= readLine()!!.split(" ").map { it.toInt() }

    city= Array(N){(Array(N){0})}
    dist= Array(N){Array(N){Array(13){0}}}
    comb= MutableList(0){List(M){(0)} }

    for(i in 0 until N){
        city[i]= readLine()!!.split(" ").map {
            it.toInt()
        }.toTypedArray()
    }//입력받기


    for(i in 0 until N){
        for(j in 0 until N){
            if(city[i][j]==2){
                chickenStore[storeCount++]=Pair(i,j)// chicken집 기록
            }
        }
    }

    for(i in 0 until storeCount){
        var (storeY,storeX)=chickenStore[i]

        for(j in 0 until N){
            for(k in 0 until N){
                if(city[j][k]==1){
                    dist[j][k][i]=calDist(k,j,storeX,storeY)
                }
            }
        }

    }//모든집에서 모든 chicken 집에 대해 거리 계산

    combination(0,M)
    var sum=0
    var answer=9999999

    for(i in comb){
        var minDist=999999
        sum=0
        for(l in 0 until N){
            for(t in 0 until N){
                minDist=999999
                for(j in 0 until M){
                    if(minDist>dist[l][t][i[j]]){
                        minDist=dist[l][t][i[j]]
                    }
                }
                sum+=minDist
            }
        }

        if(answer>sum){
            answer=sum
        }

    }


    println(answer)

}