lateinit var NM:Array<Int>
lateinit var city:Array<Array<Int>>
lateinit var tmpCity:Array<Array<Int>>

lateinit var visit:Array<Array<Boolean>>

var dy=arrayOf(0,-1,1,0)
var dx= arrayOf(1,0,0,-1)
var answer=0

fun congest(y:Int,x:Int){
    if(y<NM[0] && y>-1 && x<NM[1] && x>-1){
        //bound check

        if(visit[y][x]){
            return
        }// redundancy check

        if(tmpCity[y][x]==0 || tmpCity[y][x]==2){
            visit[y][x]=true
            tmpCity[y][x]=2

            for(i in 0 until 4){
                congest(y+dy[i],x+dx[i])
            }
        }

        else{
            return
        }

    }else{
        return
    }

}
fun comb(y:Int,x:Int,len:Int){
    if(len==0){
        var safe=0
        for(i in 0 until NM[0]){
            for(j in 0 until NM[1]){
                city[i][j]=tmpCity[i][j]
            }
        }



        for(i in 0 until NM[0]){
            for(j in 0 until NM[1]){
                if(tmpCity[i][j]==2){
                    congest(i,j)
                }
            }
        }




        for(i in 0 until NM[0]){
            for(j in 0 until NM[1]){
                if(tmpCity[i][j]==0){
                    safe++
                }
            }
        }

        if(safe>answer){

            answer=safe
        }

        for(i in 0 until NM[0]){
            for(j in 0 until NM[1]){
                tmpCity[i][j]=city[i][j]
                visit[i][j]=false
            }
        }

        return
    }


    for(i in y until NM[0]){
        for(j in x until NM[1]){
            if(tmpCity[i][j]==0){
                tmpCity[i][j]=3
                comb(i,j+1,len-1)
                tmpCity[i][j]=0
            }
        }
        comb(i+1,0,len)
    }


}
fun main(){
    NM= readLine()!!.split(" ").map{it.toInt()}.toTypedArray()

    city=Array(NM[0]){Array(NM[1]){0} }
    tmpCity=Array(NM[0]){Array(NM[1]){0} }


    visit=Array(NM[0]){Array(NM[1]){false} }

    for(i in 0 until NM[0]){
        city[i] = readLine()!!.split(" ").map { it.toInt() }.toTypedArray()
    }



    for(i in 0 until NM[0]){
        for(j in 0 until NM[1]){
            tmpCity[i][j]=city[i][j]
        }
    }


    comb(0,0,3)
    println(answer)



}