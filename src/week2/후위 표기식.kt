package src.week2

/**
 * 백준 1918번
 * https://www.acmicpc.net/problem/1918
 * 스택
 */
import java.util.Stack

fun main() {
    val expression = readln()
    val result = Stack<Char>()
    val operations = Stack<Char>()

    for (ch in expression) {
        if (ch.isLetter()) {
            result.push(ch)
        } else {
            if (ch == '+' || ch == '-') {
                while (operations.isNotEmpty() && operations.peek() != '(') {
                    result.push(operations.pop())
                }
                operations.push(ch)
            } else if (ch == '*' || ch == '/') {
                if (operations.isNotEmpty() && (operations.peek() == '*' || operations.peek() == '/')) {
                    result.push(operations.pop())
                }
                operations.push(ch)
            } else if (ch == ')') {
                while (operations.peek() != '(') {
                    result.push(operations.pop())
                }
                operations.pop()
            } else if (ch == '(') {
                operations.push(ch)
            }
        }
    }

    while (operations.isNotEmpty()) {
        result.push(operations.pop())
    }
    print(result.joinToString(""))
}