package ai

import Position
import Turn

fun playOut(position: Position, ai: Ai): Turn? {
    while (true) {
        if (position.isDraw()) return null

        val winner = position.board.getWinner()
        if (winner != null) return winner

        position.update(ai.think(position.clone()))
    }
}