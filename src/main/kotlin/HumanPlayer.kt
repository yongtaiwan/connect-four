package com.company

import COLUMNS
import Move
import Player
import Position
import java.util.*

class HumanPlayer(override val name: String) : Player {
    override val description: String
        get() = "Human input from console"

    override fun play(position: Position): Move {
        print("$name (${position.turn}), pick a numero between 1 and $COLUMNS: ")
        return Move(readColumn())
    }

    private fun readColumn(): Int {
        while (true) {
            try {
                val myInput = Scanner(System.`in`)
                val a = myInput.nextInt()
                if (a in 1..COLUMNS) return a
            } catch (e: InputMismatchException) {
            }
            println("actually choose a integer between 1 and $COLUMNS you  dingus")
        }
    }
}