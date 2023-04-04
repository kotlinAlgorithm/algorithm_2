fun main(){
    val (a,b,c)= readln().split(" ").map { it.toLong() }
    println(binaryMutiply(a,b,c))

}
fun binaryMutiply(a:Long,ex:Long,c:Long):Long{
    if(ex==1L){
        return a%c
    }

    val k=binaryMutiply(a,ex/2,c)%c
    if(ex%2==1L){
        return k*k%c*a%c
    }else{
        return k*k%c
    }
}