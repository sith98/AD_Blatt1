@file:Suppress("NAME_SHADOWING")

fun gcd(a: Int, b: Int): Int {
    var a = a
    var b = b
    var r: Int
    do {
        r = a % b
        a = b
        b = r
    } while (r != 0)
    return a
}

tailrec fun gcdRec(a: Int, b: Int): Int =
    if (b == 0) a
    else gcdRec(b, a % b)

fun lcm(a: Int, b: Int) = a * b / gcd(a, b)

fun main(args: Array<String>) {
    val pairs = listOf(
        3 to 5,
        5 to 3,
        15 to 21,
        21 to 15
    )

    for ((a, b) in pairs) {
        val result = gcd(a, b)
        val recResult = gcdRec(a, b)
        val lcmResult = lcm(a, b)
        println("gcd($a, $b) = $result")
        println("gcdRec($a, $b) = $recResult")
        println("lcm($a, $b) = $lcmResult")
        println("--------")
    }
}