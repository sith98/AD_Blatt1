fun primesTo(k: Int): Sequence<Int> {
    val numbers = IntArray(k - 1) { it + 2 }

    for ((i, number) in numbers.withIndex()) {
        if (number == 0) continue
        for (j in i + number until numbers.size step number) {
            numbers[j] = 0
        }
    }

    return numbers.asSequence().filter { it != 0 }
}

fun main(args: Array<String>) {
    val test: Sequence<Int>

    primesTo(100_000).forEach { println(it) }
}