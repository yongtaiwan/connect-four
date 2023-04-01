package ai

import Move
import Position
import mu.KotlinLogging
import java.lang.Integer.MIN_VALUE
import java.lang.Integer.max
import java.util.*

class AiBooster(val ai: Ai, private val playOuts: Int = 100) : Ai {
    override val name: String
        get() = "${ai.name} boosted"

    override val description: String
        get() = "Uses Monte-Carlo to simulate the play-outs of an AI"

    override fun think(position: Position): Move {
        val winning = winningMove(position)
        if (winning != null) return winning

        val block = blockMove(position)
        if (block != null) return block

        val validMoves = validMoves(position.clone())
        logger.debug { "turn: ${position.turn}, validMoves: $validMoves" }

        val n = max(1, playOuts / validMoves.size)
        logger.debug { "n: $n" }
        val move2score = TreeMap<Move, Int>()
        for (move in validMoves) {
            move2score[move] = 0

            val p = position.clone()
            assert(p.update(move))
            for (i in 1..n) {
                val winner = playOut(p.clone(), ai)
                if (winner == null) continue
                if (winner == position.turn) move2score[move] = move2score[move]!! + 1
                else move2score[move] = move2score[move]!! - 1
            }
            if (move2score[move] == n) return move
        }

        return findBestMove(move2score)
    }
    private val logger = KotlinLogging.logger {}

    private fun findBestMove(move2score: Map<Move, Int>): Move {
        logger.debug { "findBestMove($move2score)" }
        var bestScore = MIN_VALUE
        var bestMoves: MutableSet<Move> = TreeSet<Move>()
        for ((move, score) in move2score) {
            if (score < bestScore) continue
            if (score == bestScore) bestMoves.add(move)
            else {
                bestScore = score
                bestMoves = mutableSetOf(move)
            }
        }
        return bestMoves.random()
    }
}

