fun main(){
    while(true){
        val input= readln().split(" ").map { it.toInt() }
        if(input[0]==0){
            break
        }
        comb(input.subList(1,input.size),6,0,BooleanArray(input.size-1){false})
        println()
    }


}
fun comb(lis:List<Int>,target:Int,start:Int,visit:BooleanArray){
    if(target==0){
        println(lis.filterIndexed{idx,value-> visit[idx]}.joinToString(" "))
        return
    }
    for(i in start until visit.size){
        visit[i]=true
        comb(lis,target-1,i+1,visit)
        visit[i]=false
    }
}