package ai

import Move
import Player
import Position

class WinRandom : Ai {
    override val name: String
        get() = "Winnie"
    override val description: String
        get() = "Finds a winning move, otherwise plays randomly in a non-full column"

    override fun think(position: Position): Move {
        val winning = winningMove(position)
        if (winning != null) return winning

        return randomValidMove(position)
    }
}