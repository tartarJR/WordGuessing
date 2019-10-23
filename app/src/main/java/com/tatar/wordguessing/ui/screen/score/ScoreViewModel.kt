package com.tatar.wordguessing.ui.screen.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: String) : ViewModel() {

    private var _score = MutableLiveData<String>()
    val score: LiveData<String> get() = _score

    init {
        _score.value = finalScore
    }
}