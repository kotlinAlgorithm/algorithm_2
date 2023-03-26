fun postfix(post: String) {
    val stack = mutableListOf<Char>()

    for (i in post.indices) {
        when (val ch = post[i]) {
            '(' -> stack.add(ch)
            '+', '-' -> {
                while (stack.isNotEmpty() && stack.last() != '(') {
                    print(stack.removeLast())
                }
                stack.add(ch)
            }
            '*', '/' -> {
                while (stack.isNotEmpty() && stack.last() != '(' && (stack.last() == '*' || stack.last() == '/')) {
                    print(stack.removeLast())
                }
                stack.add(ch)
            }
            ')' -> {
                while (stack.isNotEmpty() && stack.last() != '(') {
                    print(stack.removeLast())
                }
                stack.removeLastOrNull()
            }
            in 'A'..'Z' -> print(ch)
        }
    }

    while (stack.isNotEmpty()) {
        print(stack.removeLast())
    }
}

fun main() {
    val post = readLine() ?: return
    postfix(post)
}
