const val ROWS = 6
const val COLUMNS = 7

class Board {
    private val grid: Array<Array<Turn?>> = Array(COLUMNS) { Array(ROWS) { null } }
    private var numFilled: Int = 0

    fun clone(): Board {
        val b = Board()
        b.numFilled = numFilled
        for (row in 0 until ROWS)
            for (col in 0 until COLUMNS)
                b.grid[col][row] = grid[col][row]
        return b
    }

    // 1️⃣2️⃣3️⃣4️⃣5️⃣6️⃣️7️⃣
    private fun buildRuler(builder: StringBuilder) {
        val zero = '\u0030'
        val suffix = "\uFE0F\u20E3"
        for (col in 1..COLUMNS)
            builder.append(zero + col + suffix)
    }

    override fun toString(): String {
        val builder = StringBuilder()
        buildRuler(builder)
        builder.append('\n')

        for (row in ROWS-1 downTo 0) {
            for (col in 0 until COLUMNS) {
                val turn = grid[col][row]
                builder.append(turn?.emoji ?: "⬜️")
            }
            builder.append('\n')
        }
        buildRuler(builder)
        return builder.toString()
    }

    fun update(turn: Turn, move: Move): Boolean {
        val col = move.column - 1
        for (row in 0 until ROWS) {
            if (grid[col][row] == null) {
                grid[col][row] = turn
                numFilled++
                return true
            }
        }
        return false
    }

    fun isFull(): Boolean {
        return numFilled == ROWS * COLUMNS
    }

    fun getRow3(): Turn? {
        for (row in 0 until ROWS) {
            for (col in 0 until COLUMNS) {
                val color = grid[col][row]
                if (color != null && check(row, col, color, 2)) {
                    return color
                }
            }
        }
        return null
    }

    fun getWinner(): Turn? {
        for (row in 0 until ROWS) {
            for (col in 0 until COLUMNS) {
                val color = grid[col][row]
                if (color != null && check(row, col, color, 3)) {
                    return color
                }
            }
        }
        return null
    }

    private fun check(row: Int, col: Int, color: Turn, stepsy: Int): Boolean {
        assert(stepsy in 1..3)
        assert(row in 0 until ROWS)
        assert(col in 0 until COLUMNS)
        for (deltaRow in -1..1) {
            for (deltaCol in -1..1) {
                if (deltaRow == 0 && deltaCol == 0) continue
                var match = true
                for (step in 1..stepsy) {
                    val r = row + step * deltaRow
                    if (r !in 0 until ROWS) {
                        match = false
                        break
                    }
                    val c = col + step * deltaCol
                    if (c !in 0 until COLUMNS) {
                        match = false
                        break
                    }
                    if (grid[c][r] != color) {
                        match = false
                        break
                    }
                }
                if (match) {
                    return true
                }
            }
        }
        return false
    }
}