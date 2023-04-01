package ai

import Move
import Player
import Position

class RandomValid : Ai {
    override val name: String
        get() = "Dumbo"
    override val description: String
        get() = "Plays randomly in a non-full column"

    override fun think(position: Position): Move {
        return randomValidMove(position)
    }
}