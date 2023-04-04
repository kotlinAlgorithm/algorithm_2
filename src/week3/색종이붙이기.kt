lateinit var paperMap: Array<IntArray>
lateinit var visit: Array<BooleanArray>
var paperNumList = intArrayOf(5, 5, 5, 5, 5)

var answer=100

fun main() {
    paperMap = Array(10) { readln().split(" ").map { it.toInt() }.toIntArray() }
    visit = Array(10) { BooleanArray(10) { false } }


    dfs(0,0,0)

    if(answer==100){
        println(-1)
    }else{
        println(answer)
    }
}

fun dfs(y:Int,x:Int,paperCnt:Int){
    if(y>9){
        answer=minOf(answer,paperCnt)
        return
    }
    if(paperMap[y][x]==0){
        var nextX=x+1
        var nextY=y

        if(nextX==10){
            nextX=0
            nextY=y+1
        }

        dfs(nextY,nextX,paperCnt)
        return
    }

    for(size in 5 downTo 1){
        if(checkPossible(size,y,x) && paperNumList[size-1]>0){
            updatePaperState(y,x,size,0)
            paperNumList[size-1]--
            var nextX=x+1
            var nextY=y

            if(nextX==10){
                nextX=0
                nextY=y+1
            }

            dfs(nextY,nextX,paperCnt+1)
            updatePaperState(y,x,size,1)
            paperNumList[size-1]++

        }
    }

}
fun updatePaperState(y:Int,x:Int,size:Int,state:Int){
    for(i in y until y+size){
        for(j in x until x+size){
            paperMap[i][j]=state
        }
    }
}

fun checkPossible(size: Int, y: Int, x: Int): Boolean {
    for (i in y until y + size) {
        if (i > 9) {
            return false
        }
        for (j in x until x + size) {
            if (j > 9) {
                return false
            }
            if (paperMap[i][j] == 0) {
                return false
            }
        }
    }

    return true
}