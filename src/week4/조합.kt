import java.math.BigInteger

fun main(){
    val (n,m)= readln().split(" ").map { it.toInt() }
    pascal(n)
    println(pascalArr[n][m])
}
lateinit var pascalArr :MutableList<MutableList<BigInteger>>
fun pascal(n:Int){
    val one=BigInteger.ONE
    pascalArr= MutableList(n+1){ mutableListOf() }
    pascalArr[0].add(one)
    pascalArr[1].add(one)
    pascalArr[2].add(one);pascalArr[2].add(BigInteger.valueOf(2));pascalArr[2].add(one)
    for(y in 3..n){
        pascalArr[y].add(one)
        for(x in 1 until y){
            pascalArr[y].add(pascalArr[y-1][x-1]+pascalArr[y-1][x])
        }
        pascalArr[y].add(one)
    }

}