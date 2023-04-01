package ai

import Move
import Player
import Position

interface Ai : Player {
    override fun play(position: Position): Move {
        println("$name (${position.turn}) is thinking...")
        return think(position)
    }

    fun think(position: Position): Move
}