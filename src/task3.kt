import kotlin.system.measureTimeMillis

class Matrix(val height: Int, val width: Int) {
    val size = height * width

    private lateinit var field: IntArray

    fun init(): Matrix = this.apply {
        field = IntArray(size)
    }

    operator fun get(row: Int, col: Int) = field[row * width + col]
    operator fun set(row: Int, col: Int, value: Int) {
        field[row * width + col] = value
    }

    fun init(string: String): Matrix = this.apply {
        val newField = string.split(" ")
            .map { it.toInt() }
            .toIntArray()

        require(newField.size == size) {
            "matrix input has to contain ${width * height} integers"
        }

        field = newField
    }

    fun initRandom(): Matrix = this.init().apply {
        val r = java.util.Random()
        for (i in 0 until field.size) {
            field[i] = r.nextInt(10)
        }
    }

    fun input(): Matrix = this.apply {
        init(readLine()!!)
    }

    operator fun plus(m: Matrix): Matrix {
        var numAdditions = 0
        var numMultiplications = 0

        require(m.width == width && m.height == height) {
            "Matrix dimensions don't match"
        }

        val newField = IntArray(size)

        for (i in 0 until size) {
            numAdditions++
            newField[i] = field[i] + m.field[i]
            numAdditions++
        }

        println("additions: $numAdditions, multiplications: $numMultiplications")

        return Matrix(width, height).also { it.field = newField }
    }

    operator fun times(m: Matrix): Matrix {
        require(width == m.height) {
            "Matrix dimensions don't match"
        }
        var numAdditions = 0
        var numMultiplications = 0

        val newMatrix = Matrix(height, m.width).init()

        for (col in 0 until newMatrix.width) {
            for (row in 0 until newMatrix.height) {
                var sum = 0
                for (i in 0 until width) {
                    numAdditions += 3
                    numMultiplications += 1
                    sum += this[row, i] * m[i, col]
                    numAdditions++
                }

                newMatrix[row, col] = sum

                numAdditions++
            }
            numAdditions++
        }

        println("additions: $numAdditions, multiplications: $numMultiplications")

        return newMatrix
    }

    override fun toString(): String {
        return field
            .asIterable()
            .chunked(width) { it.joinToString(" ") }
            .joinToString("\n")
    }

}

fun main(args: Array<String>) {
//    val a = Matrix(2, 3).init("3 2 1 1 0 2")
//    val b = Matrix(3, 2).init("1 2 0 1 4 0")
//
//    val test = Matrix(80, 80).initRandom()
//    println(test * test)

    (100..10000 step 100).map {n ->
        val a = Matrix(n, n).initRandom()
        val b = Matrix(n, n).initRandom()
        n to measureTimeMillis {
            a + b
        }
    }.forEach { (n, time) -> println("$n\t$time") }
}

















