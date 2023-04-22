package src.week7

/**
 * https://www.acmicpc.net/problem/5052
 * (1) 알파벳 and (2) 길이 기준 오름차순 '정렬' + 인접한 번호끼리만 접두어 검사
 * 문자열 정렬은 기본적으로 알파벳과 길이가 기준이 된다.
 * 문자열 기본 정렬을 수행하면 번호마다 한 번의 비교로 자신이 접두어인지 여부를 확인할 수 있다.
 * Why? 문자열 기본 정렬 방식과 접두어 비교 방식이 완전히 같기 때문이다: 왼쪽부터 각 문자를 아스키 코드 기준으로 비교함
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
        if (numbers[i].length <= numbers[i + 1].length) {
            if (numbers[i] == numbers[i + 1].substring(0, numbers[i].length)) {
                return true
            }
        }
    }

    return false
}
