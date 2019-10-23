package com.tatar.wordguessing.ui.screen.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tatar.wordguessing.data.Score
import com.tatar.wordguessing.data.ScoreManager
import com.tatar.wordguessing.data.WordProvider

class GameViewModel : ViewModel() {

    private lateinit var wordProvider: WordProvider // TODO use DI
    private lateinit var scoreManager: ScoreManager // TODO use DI

    private var _word = MutableLiveData<String>()
    private var _score = MutableLiveData<Score>()
    private var _eventEndGame = MutableLiveData<Boolean>()

    val word: LiveData<String> get() = _word
    val score: LiveData<Score> get() = _score
    val eventEndGame: LiveData<Boolean> get() = _eventEndGame

    init {
        initWord()
        initScore()

        _eventEndGame.value = false
    }

    fun onCorrectAnswer() {
        scoreManager.increaseScore()
        _score.value = scoreManager.getScore()
        updateWordIfWordsListNotEmpty()
    }

    fun onSkipWord() {
        scoreManager.decreaseScore()
        _score.value = scoreManager.getScore()
        updateWordIfWordsListNotEmpty()
    }

    private fun updateWordIfWordsListNotEmpty() {
        if (wordProvider.isWordListNotEmpty())
            _word.value = wordProvider.getNextWord()
        else
            _eventEndGame.value = true
    }

    private fun initWord() {
        wordProvider = WordProvider()
        wordProvider.refreshList()
        _word.value = wordProvider.getNextWord()
    }

    private fun initScore() {
        scoreManager = ScoreManager()
        scoreManager.provideScore()
        _score.value = scoreManager.getScore()
    }

    fun onGameEndCompleted() {
        _eventEndGame.value = false
    }
}