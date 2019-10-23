package com.tatar.wordguessing.data

import android.os.CountDownTimer

class GameTimer(val gameAction: GameResponse) {

    private var countDownTimer: CountDownTimer

    init {
        countDownTimer = object : CountDownTimer(
            COUNTDOWN_TIME,
            ONE_SECOND_IN_MILLIS
        ) {
            override fun onTick(millisUntilFinished: Long) {
                gameAction.onNextSecond(getTimeInSeconds(millisUntilFinished))
            }

            override fun onFinish() {
                gameAction.onGameEnd()
            }
        }
    }

    fun startGameTimer() {
        countDownTimer.start()
    }

    fun stopGameTimer() {
        countDownTimer.cancel()
    }

    private fun getTimeInSeconds(time: Long): Int {
        return (time / ONE_SECOND_IN_MILLIS).toInt()
    }

    companion object {
        const val ONE_SECOND_IN_MILLIS = 1000L
        const val COUNTDOWN_TIME = 120000L
    }
}

interface GameResponse {
    fun onNextSecond(timeInSeconds: Int)
    fun onGameEnd()
}