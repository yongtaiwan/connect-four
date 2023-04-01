class Move(val column: Int) : Comparable<Move> {
    init {
        assert(column in 1..COLUMNS)
    }

    override fun toString(): String {
        return column.toString()
    }

    override operator fun compareTo(other: Move): Int {
        return column - other.column
    }
}
