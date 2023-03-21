import java.util.*
val br=System.`in`.bufferedReader()
val bw=System.`out`.bufferedWriter()
fun main() {
    val n = readLine()!!.toInt()
    val maxHeap = PriorityQueue<Int>(compareByDescending { it })
    val minHeap = PriorityQueue<Int>()

    for (i in 1..n) {
        val num = br.readLine().toInt()

        if (maxHeap.size == minHeap.size) {
            maxHeap.offer(num)
        } else {
            minHeap.offer(num)
        }

        if (minHeap.isNotEmpty() && maxHeap.peek() > minHeap.peek()) {
            val max = maxHeap.poll()
            val min = minHeap.poll()
            maxHeap.offer(min)
            minHeap.offer(max)
        }

        bw.write("${maxHeap.peek()}\n")
    }
    bw.close()
    br.close()
}

/*
import java.util.PriorityQueue

val inQ = PriorityQueue<Int>()
val outQ = PriorityQueue<Int>(compareBy { -it })
val br = System.`in`.bufferedReader()
val bw = System.`out`.bufferedWriter()
fun main() {
    val n = readln().toInt()
    for (i in 0 until n) {
        val num = br.readLine().toInt()
        inQ.add(num)

        while (outQ.isNotEmpty()&&inQ.isNotEmpty()&&(outQ.peek() > inQ.peek())) {
            inQ.add(outQ.poll())
        }

        while (inQ.size > outQ.size) {
            outQ.add(inQ.poll())
        }
        if (inQ.isNotEmpty()) {
            bw.write("${minOf(inQ.peek(), outQ.peek())}\n")
        } else {
            bw.write("${outQ.peek()}\n")

        }
    }

    br.close()
    bw.close()

}



*/