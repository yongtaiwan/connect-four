import ai.Ai

class Game(private val red: Player, private val yellow: Player) {
    private val position: Position = Position();
    fun run(): Turn? {
        println(position)
        while (true) {
            val whoseTurn = position.turn
            val player = when (whoseTurn) {
                Turn.RED -> red
                Turn.YELLOW -> yellow
            }
            var doneMove: Boolean
            do {
                val move = player.play(position.clone()) // Make a copy so that player cannot hack the game position directly
                doneMove = position.update(move)
                if (!doneMove) {
                    println("that column is full don't you have eyes stupid?")
                    continue
                }

                if (player is Ai) {
                    println("${player.name} ($whoseTurn) plays on column $move.")
                }
                println(position)

                if (position.isDraw()) {
                    println("It's a draw.")
                    return null
                }

                val winner = position.board.getWinner()
                if (winner != null) {
                    println("${player.name} (${winner.name}) wins!")
                    return winner
                }
            } while (!doneMove)
        }
    }
}