import mu.KotlinLogging

data class Position(var board: Board = Board(), var turn: Turn = Turn.RED) {
    fun clone(): Position {
        return Position(board.clone(), turn)
    }

    override fun toString(): String {
        return board.toString()
    }

    fun isDraw(): Boolean {
        return board.isFull()
    }

    fun update(move: Move): Boolean {
        if (!board.update(turn, move)) {
            return false
        }
        switchTurn()
        return true
    }

    fun updateCopy(move: Move): Position {
        val newPosition = clone()
        assert(newPosition.update(move))
        return newPosition
    }

    fun switchTurn() {
        turn = when(turn) {
            Turn.RED -> Turn.YELLOW
            Turn.YELLOW -> Turn.RED
        }
    }

    private val logger = KotlinLogging.logger {}
}
