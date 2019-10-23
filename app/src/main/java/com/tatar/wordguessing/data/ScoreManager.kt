package com.tatar.wordguessing.data

class ScoreManager {

    private var score = 0

    fun getScore(): Int {
        return score
    }

    fun increaseScore(): Int {
        score++
        return score
    }

    fun decreaseScore(): Int {
        score--
        return score
    }
}