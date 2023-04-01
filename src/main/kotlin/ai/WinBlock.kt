package ai

import Move
import Player
import Position

class WinBlock : Ai {
    override val name: String
        get() = "Blok"
    override val description: String
        get() = "Finds a winning move, then finds a blocking move, otherwise plays randomly in a non-full column"

    override fun think(position: Position): Move {
        val winning = winningMove(position)
        if (winning != null) return winning

        val block = blockMove(position)
        if (block != null) return block

        return randomValidMove(position)
    }
}