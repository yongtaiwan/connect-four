package ai

import COLUMNS
import Move
import Position

fun randomMove(): Move {
    return Move((1..COLUMNS).random())
}

fun validMoves(position: Position): List<Move> {
    val moves = ArrayList<Move>()
    for (col in 1..COLUMNS) {
        val move = Move(col)
        if (position.update(move)) moves.add(move)
    }
    return moves
}

fun randomValidMove(position: Position): Move {
    while (true) {
        val move = randomMove()
        if (position.update(move)) return move
    }
}

fun winningMove(position: Position): Move? {
    for (col in 1..COLUMNS) {
        val move = Move(col)
        val b = position.board.clone()
        val whoseTurn = position.turn
        if (!b.update(whoseTurn, move)) continue
        if (b.getWinner() == whoseTurn) {
            return move
        }
    }
    return null
}

fun winny2(position: Position): Move? {
    for (col in 1..COLUMNS) {
        val move = Move(col)
        val b = position.board.clone()
        val whoseTurn = position.turn
        if (!b.update(whoseTurn, move)) continue
        if (b.getRow3() == whoseTurn) {
            return move
        }
    }
    return null
}

fun blockMove(position: Position): Move? {
    val p = position.clone()
    p.switchTurn()
    return winningMove(p)
}
