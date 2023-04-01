import ai.*
import com.company.HumanPlayer
import java.util.*

//fun aiTournament() {
//    val ais = arrayOf(Random(), RandomValid(), WinRandom(), WinBlock())
//    data class Stat(var win: Int, var draw: Int, var loss: Int)
//    val result = HashMap<Player, HashMap<Player, Stat>>()
//    for (redAI in ais) {
//        result.compute()
//        for (yellowAI in ais) {
//            result
//        }
//    }
//    for (redAI in ais) {
//        for (yellowAI in ais) {
//            val stat = result[redAI]?.get(yellowAI)
//            when (Game(redAI, yellowAI).run()) {
//                null -> ++stat.draw
//                Turn.RED -> ++stat.win
//                Turn.YELLOW -> ++stat.loss
//            }
//            println("${redAI.name} vs ${yellowAI.name}: win $aiWin, draw $draw, loss $ai3Win")
//        }
//    }
//}

fun main() {
    val ai1 = RandomValid()
    val ai2 = WinRandom()
    val ai3 = WinBlock()
    val ai4 = WinBlock2()
    val ai4boosted = AiBooster(ai4, 10000)
    val ai3boosted = AiBooster(ai3, 10000)
    val ai2boosted = AiBooster(ai2, 10000)
    val ai1boosted = AiBooster(ai1, 10000)
    val ai: Ai

//    Game(ai4boosted, ai3boosted).run()

    println("Hello fello contestant of the 31st noobfest, may I ask what your name is?")
    val myInput = Scanner(System.`in`)
    val name = myInput.next()

    println("Hello $name pick your difficulty of easy, medium, hard, impossible")

    var diff = myInput.next()

    while (true) {
        if (diff == "easy") {
            ai = ai2
            break
        } else if (diff == "medium") {
            ai = ai3
            break
        } else if (diff == "hard") {
            ai = ai4
            break
        } else if (diff == "impossible") {
            ai = ai3boosted
            break
        } else
            println("actually choose one you muffin head")
        diff = myInput.next()
    }

    Game(HumanPlayer(name), ai).run()
}















