package ai

import Move
import Player
import Position

class Random(override val name: String = "random") : Ai {
    override val description: String
        get() = "Always plays randomly, including full columns"

    override fun think(position: Position): Move {
        return randomMove()
    }
}

