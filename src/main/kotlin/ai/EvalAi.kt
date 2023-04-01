package ai

import Move
import Player
import Position


class EvalAi : Ai {
    override val name: String
        get() = "Blokker"
    override val description: String
        get() = "Finds a winning move, then finds a blocking move, otherwise plays randomly in a non-full column"

    override fun think(position: Position): Move {

        val moves = validMoves(position)
        var highestscore = -2.0
        var lowestscore = 2.0
        var bestMove: Move? = null
        for (move in moves) {
            val score = evaluator.evaluate(position.updateCopy(move))
            when (position.turn) {
                Turn.RED -> {
                    if (score > highestscore) {
                        highestscore = score
                        bestMove = move
                    }
                }
                Turn.YELLOW -> {
                    if (score < lowestscore) {
                        lowestscore = score
                        bestMove = move
                    }
                }
            }
        }
        return bestMove!!
    }

    private val evaluator = SimpleEvaluator()
}