package com.tatar.wordguessing.ui.screen.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {

    private var _score = MutableLiveData<Int>()
    private var _restartGameEvent = MutableLiveData<Boolean>()

    val score: LiveData<Int> get() = _score
    val scoreString = Transformations.map(score) { it.toString() }
    val restartGameEvent: LiveData<Boolean> get() = _restartGameEvent

    init {
        _score.value = finalScore
    }

    fun onRestartGame() {
        _restartGameEvent.value = true
    }

    fun onRestartGameComplete() {
        _restartGameEvent.value = false
    }
}