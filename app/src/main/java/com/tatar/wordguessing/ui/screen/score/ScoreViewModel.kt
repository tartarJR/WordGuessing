package com.tatar.wordguessing.ui.screen.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: String) : ViewModel() {

    private var _score = MutableLiveData<String>()
    private var _restartGameEvent = MutableLiveData<Boolean>()

    val score: LiveData<String> get() = _score
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