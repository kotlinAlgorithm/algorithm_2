import java.util.*

lateinit var gameMap:Array<Array<String>>
val q= LinkedList<QItem>()
var holeY=0
var holeX=0
var n=0
var m=0
data class Pos(var y:Int,var x:Int)
data class QItem(val red:Pos,val blue:Pos,val depth:Int)

fun main(){
    val (n_,m_)= readln().split(" ").map { it.toInt() }
    var initialRedY=0
    var initialRedX=0
    var initialBlueY=0
    var initialBlueX=0
    n=n_
    m=m_

    gameMap=Array(n){readln().chunked(1).toTypedArray() }

    for(i in 0 until n){
        for(j in 0 until m){
            if(gameMap[i][j]=="R"){
                initialRedY=i
                initialRedX=j
            }else if(gameMap[i][j]=="B"){
                initialBlueY=i
                initialBlueX=j
            }else if(gameMap[i][j]=="O"){
                holeY=i
                holeX=j
            }
        }
    }
 /*   println("$initialRedY $initialRedX $initialBlueY $initialBlueX")
    println(gravityRight(Pos(initialRedY,initialRedX),Pos(initialBlueY,initialBlueX),0))
    println(q.poll())*/
    q.add(QItem(Pos(initialRedY,initialRedX),Pos(initialBlueY,initialBlueX),0))
    while(q.isNotEmpty()){
        val (red,blue,depth)=q.poll()
        if(depth==10){
            println(-1)
            break
        }
        val down=gravityDown(red,blue,depth)
        if(down>0){
            println(down)
            break
        }
        val up=gravityUp(red,blue,depth)
        if(up>0){
            println(up)
            break
        }
        val left=gravityLeft(red,blue,depth)
        if(left>0){
            println(left)
            break
        }
        val right=gravityRight(red,blue,depth)
        if(right>0){
            println(right)
            break
        }

    }



}

fun gravityDown(redNow:Pos,blueNow:Pos,depth:Int):Int{

    var blue:Pos=Pos(0,0)
    var red:Pos=Pos(0,0)
    var ret=0
    fun blueDown(){
        for(i in blueNow.y+1 until n){
            if(gameMap[i][blueNow.x]=="O"){
                ret=-1
            }else if(gameMap[i][blueNow.x]=="#"){
                blue=Pos(i-1,blueNow.x)
                break
            }else if(i==red.y && blueNow.x==red.x){
                blue=Pos(i-1,blueNow.x)
                break
            }
        }
    }
    fun redDown(){
        for(i in redNow.y+1 until n){
            if(gameMap[i][redNow.x]=="O"){
                ret=depth+1
            }else if(gameMap[i][redNow.x]=="#"){
                red=Pos(i-1,redNow.x)
                break
            }else if(i==blue.y && redNow.x==blue.x){
                red=Pos(i-1,redNow.x)
                break
            }
        }
    }

    if(redNow.y<blueNow.y){
        blueDown()
        if(ret==-1) return ret
        redDown()
    }else{
        redDown()
        blueDown()
    }
    if(ret==0){
        q.add(QItem(red,blue,depth+1))
    }
    return ret
}
fun gravityUp(redNow:Pos,blueNow:Pos,depth:Int):Int{

    var blue:Pos=Pos(0,0)
    var red:Pos=Pos(0,0)
    var ret=0
    fun blueUp(){
        for(i in blueNow.y-1 downTo 0){
            if(gameMap[i][blueNow.x]=="O"){
                ret=-1
            }else if(gameMap[i][blueNow.x]=="#"){
                blue=Pos(i+1,blueNow.x)
                break
            }else if(i==red.y && blueNow.x==red.x){
                blue=Pos(i+1,blueNow.x)
                break
            }
        }
    }
    fun redUp(){
        for(i in redNow.y-1 downTo 0){
            if(gameMap[i][redNow.x]=="O"){
                ret=depth+1
            }else if(gameMap[i][redNow.x]=="#"){
                red=Pos(i+1,redNow.x)
                break
            }else if(i==blue.y && redNow.x==blue.x){
                red=Pos(i+1,redNow.x)
                break
            }
        }
    }
    if(redNow.y<blueNow.y){
        redUp()
        blueUp()
    }else{
        blueUp()
        if(ret==-1) return ret
        redUp()
    }
    if(ret==0){
        q.add(QItem(red,blue,depth+1))
    }
    return ret
}
fun gravityRight(redNow:Pos,blueNow:Pos,depth:Int):Int{

    var blue:Pos=Pos(0,0)
    var red:Pos=Pos(0,0)
    var ret=0
    fun blueRight(){
        for(i in blueNow.x+1 until m){
            if(gameMap[blueNow.y][i]=="O"){
                ret=-1
            }else if(gameMap[blueNow.y][i]=="#"){
                blue=Pos(blueNow.y,i-1)
                break
            }else if(blueNow.y==red.y && i==red.x){
                blue=Pos(blueNow.y,i-1)
                break
            }
        }
    }
    fun redRight(){
        for(i in redNow.x+1 until m){
            if(gameMap[redNow.y][i]=="O"){
                ret=depth+1
            }else if(gameMap[redNow.y][i]=="#"){
                red=Pos(redNow.y,i-1)
                break
            }else if(redNow.y==blue.y && i==blue.x){
                red=Pos(redNow.y,i-1)
                break
            }
        }
    }

    if(redNow.x<blueNow.x){
        blueRight()
        if(ret==-1) return ret
        redRight()
    }else{
        redRight()
        blueRight()
    }
    if(ret==0){
        q.add(QItem(red,blue,depth+1))
    }
    return ret
}
fun gravityLeft(redNow:Pos,blueNow:Pos,depth:Int):Int{


    var blue:Pos=Pos(0,0)
    var red:Pos=Pos(0,0)
    var ret=0
    fun blueLeft(){
        for(i in blueNow.x-1 downTo 0){
            if(gameMap[blueNow.y][i]=="O"){
                ret=-1
            }else if(gameMap[blueNow.y][i]=="#"){
                blue=Pos(blueNow.y,i+1)
                break
            }else if(blueNow.y==red.y && i==red.x){
                blue=Pos(blueNow.y,i+1)
                break
            }
        }
    }
    fun redLeft(){
        for(i in redNow.x-1 downTo 0){
            if(gameMap[redNow.y][i]=="O"){
                ret=depth+1
            }else if(gameMap[redNow.y][i]=="#"){
                red=Pos(redNow.y,i+1)
                break
            }else if(redNow.y==blue.y && i==blue.x){
                red=Pos(redNow.y,i+1)
                break
            }
        }
    }

    if(redNow.x<blueNow.x){
        redLeft()
        blueLeft()
    }else{
        blueLeft()
        if(ret==-1) return ret
        redLeft()
    }
    if(ret==0){
        q.add(QItem(red,blue,depth+1))
    }
    return ret
}