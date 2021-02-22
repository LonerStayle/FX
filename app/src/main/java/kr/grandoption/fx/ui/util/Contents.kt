package kr.grandoption.fx.ui.util

import java.util.*


object Contents {
    val noticeBoardRandomId: () -> String = {
        "${Random().nextInt(2147483647)}${System.currentTimeMillis()}"
    }
    val replyRandomId:()->String =
    { "reply+${Random().nextInt(2147483647)}${System.currentTimeMillis()}" }

    var startRound = 1
    const val GAME_READY_MODE = 0
    const val GAME_REMEMBER_MODE = 1
    const val GAME_START_MODE = 2
    const val GAME_ROUND_TIME = 24000L


}