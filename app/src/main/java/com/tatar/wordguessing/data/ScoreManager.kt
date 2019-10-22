package com.tatar.wordguessing.data

class ScoreManager {

    private lateinit var score: Score

    fun provideScore() {
        score = Score()
    }

    fun getScore(): Score {
        return score
    }

    fun increaseScore(): Score {
        score.finalScore++
        return score
    }

    fun decreaseScore(): Score {
        score.finalScore--
        return score
    }

    fun setTimeInSeconds(completionTimeInSeconds: Int) {
        score.completionTimeInSeconds = completionTimeInSeconds
    }
}