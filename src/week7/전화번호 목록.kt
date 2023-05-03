fun main() {
    val testCaseNum = readln().toInt()

    outerLoop@ for (i in 0 until testCaseNum) {
        val n = readln().toInt()
        val arr = Array(n) { "" }
        for (j in 0 until n) {
            arr[j] = readln()
        }
        arr.sort()

        for (now in 0 until n - 1) {
            val nowLen = arr[now].length
            if (arr[now + 1].length < nowLen) {
                continue
            }
            if (arr[now] == arr[now + 1].substring(0, nowLen)) {
                println("NO")
                continue@outerLoop
            }
        }
        println("YES")
    }
}