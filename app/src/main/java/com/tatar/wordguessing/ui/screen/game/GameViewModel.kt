package com.tatar.wordguessing.ui.screen.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tatar.wordguessing.data.*

class GameViewModel : ViewModel(), GameResponse {

    private lateinit var wordProvider: WordProvider // TODO use DI
    private lateinit var scoreManager: ScoreManager // TODO use DI
    private lateinit var gameTimer: GameTimer // TODO use DI

    private var _word = MutableLiveData<String>()
    private var _score = MutableLiveData<Int>()
    private var _timeLeft = MutableLiveData<String>()
    private var _eventEndGame = MutableLiveData<Boolean>()

    val word: LiveData<String> get() = _word
    val score: LiveData<Int> get() = _score
    val timeLeft: LiveData<String> get() = _timeLeft
    val eventEndGame: LiveData<Boolean> get() = _eventEndGame

    init {
        initWord()
        initScore()
        initGameTimer()
        initEventEndGame()
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
        if (wordProvider.isWordsEmpty())
            wordProvider.refreshList()

        _word.value = wordProvider.getNextWord()
    }

    private fun initWord() {
        wordProvider = WordProvider()
        wordProvider.refreshList()
        _word.value = wordProvider.getNextWord()
    }

    private fun initScore() {
        scoreManager = ScoreManager()
        _score.value = scoreManager.getScore()
    }

    private fun initGameTimer() {
        gameTimer = GameTimer(this)
        gameTimer.startGameTimer()
    }

    private fun initEventEndGame() {
        _eventEndGame.value = false
    }

    fun onGameEndCompleted() {
        _eventEndGame.value = false
    }

    fun getScoreString(): String {
        return score.toString()
    }

    override fun onCleared() {
        super.onCleared()
        gameTimer.stopGameTimer()
    }

    override fun onNextSecond(formattedRemainingTime: String) {
        _timeLeft.value = formattedRemainingTime
    }

    override fun onGameEnd() {
        _eventEndGame.value = true
        _timeLeft.value = NO_TIME_LEFT_STRING
    }

    companion object {
        private const val NO_TIME_LEFT_STRING = "0"
    }
}