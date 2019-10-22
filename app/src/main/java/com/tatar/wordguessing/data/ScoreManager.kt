package com.tatar.wordguessing.data

class ScoreManager {

    private lateinit var score: Score

    fun provideScore() {
        score = Score()
    }

    fun getScore(): Score {
        return score
    }

    fun increaseScore() {
        score.finalScore++
    }

    fun decreaseScore() {
        score.finalScore--
    }

    fun setTimeInSeconds(completionTimeInSeconds: Int) {
        score.completionTimeInSeconds = completionTimeInSeconds
    }
}