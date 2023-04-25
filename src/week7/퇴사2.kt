data class Cost(var day: Int, var profit: Int)

lateinit var costArr: Array<Cost>
lateinit var dpArr: IntArray
fun main() {
    val n = readln().toInt()
    costArr = Array(n + 1) { Cost(0, 0) }
    dpArr = IntArray(n + 1) { 0 }

    for (i in 1..n) {
        val (d, p) = readln().split(" ").map { it.toInt() }
        costArr[i] = Cost(d, p)
    }

    dpArr[n] = if (costArr[n].day == 1) costArr[n].profit else 0
    for (i in n - 1 downTo 1) {
        dpArr[i] = dpArr[i + 1]

        if (i + costArr[i].day - 1 > n) {
            continue
        }//회의를 진행할 수 없을 경우

        val nextPossibleDp = if (i + costArr[i].day < n + 1) dpArr[i + costArr[i].day] else 0
        dpArr[i] = maxOf(dpArr[i], costArr[i].profit + nextPossibleDp)
    }
    println(dpArr[1])
}