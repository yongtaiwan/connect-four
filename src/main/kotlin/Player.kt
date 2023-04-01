interface Player {
    val name: String
    val description: String

    fun play(position: Position): Move
}