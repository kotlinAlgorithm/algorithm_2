data class Score(var totalCnt:Int,var bullAndSingleCnt:Int)
lateinit var dpTable:Array<Score>

class Solution {
    fun solution(target: Int): IntArray {
        var answer=intArrayOf(0,0)
        dpTable=Array(100001){Score(1000000,-1)}

        dpTable[0].apply { totalCnt=0 ; bullAndSingleCnt=0 }
        for(i in 1..20){
            dpTable[i].apply { totalCnt=1 ; bullAndSingleCnt=1 }
        }
        for(i in 21..40){
            if(i%2==0 || i%3==0){
                dpTable[i].apply { totalCnt=1 ; bullAndSingleCnt=0 }
            }else{
                dpTable[i].apply { totalCnt=2;bullAndSingleCnt=2}
            }
        }
        for(i in 41..49){
            if(i%3==0){
                dpTable[i].apply { totalCnt=1 ; bullAndSingleCnt=0 }
            }else{
                dpTable[i].apply { totalCnt=2;bullAndSingleCnt=1}
            }
        }
        dpTable[50].apply { totalCnt=1;bullAndSingleCnt=1 }
        for(i in 51..60){
            if(i%3==0){
                dpTable[i].apply { totalCnt=1 ; bullAndSingleCnt=0 }
            }else{
                dpTable[i].apply { totalCnt=2;bullAndSingleCnt=2}
            }
        }


       for(i in 61..target){
            dpTable[i]=Score(dpTable[i-1].totalCnt+1,dpTable[i-1].bullAndSingleCnt+1)
            for(j in i-60 until i){
                val gap=i-j
                dpTable[i]=compareScore(Score(dpTable[j].totalCnt+dpTable[gap].totalCnt,dpTable[j].bullAndSingleCnt+dpTable[gap].bullAndSingleCnt),dpTable[i])
            }
        }

        answer[0]=dpTable[target].totalCnt
        answer[1]=dpTable[target].bullAndSingleCnt

        return answer
    }
    fun compareScore(a:Score,b:Score):Score{
        return if(a.totalCnt<b.totalCnt){
            a
        }else if(a.totalCnt>b.totalCnt){
            b
        }else{
            if(a.bullAndSingleCnt<b.bullAndSingleCnt){
                b
            }else{
                a
            }
        }
    }
}