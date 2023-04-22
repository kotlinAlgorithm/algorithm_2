package src.week7

/**
 * https://www.acmicpc.net/problem/5052
 * (1) 알파벳 and (2) 길이 기준 오름차순 '정렬' + 인접한 번호끼리만 접두어 검사
 * 문자열 정렬은 기본적으로 알파벳과 길이가 기준이 된다.
 */
fun main() {
    val t = readln().toInt()
    val answer = StringBuilder()

    repeat(t) {
        val numberCount = readln().toInt()
        val numbers = Array(numberCount) { readln() }
        numbers.sort()

        if (hasPrefix(numbers)) {
            answer.append("NO\n")
        } else {
            answer.append("YES\n")
        }
    }

    print(answer)
}

private fun hasPrefix(numbers: Array<String>): Boolean {
    for (i in 0 until numbers.lastIndex) {
        if (numbers[i].length > numbers[i + 1].length) continue

        var isPrefix = true
        for (j in numbers[i].indices) {
            if (numbers[i][j] != numbers[i + 1][j]) {
                isPrefix = false
                break
            }
        }

        if (isPrefix) return true
    }

    return false
}
