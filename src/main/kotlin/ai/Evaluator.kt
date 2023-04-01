package ai

import Position

interface Evaluator {
    fun evaluate(position: Position): Double
}