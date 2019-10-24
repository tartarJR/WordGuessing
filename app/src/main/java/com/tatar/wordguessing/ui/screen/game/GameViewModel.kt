package com.tatar.wordguessing.ui.screen.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tatar.wordguessing.data.ScoreManager
import com.tatar.wordguessing.data.WordProvider
import com.tatar.wordguessing.helper.Buzzer.BuzzType
import com.tatar.wordguessing.helper.GameResponse
import com.tatar.wordguessing.helper.GameTimer

class GameViewModel : ViewModel(), GameResponse {

    private lateinit var wordProvider: WordProvider // TODO use DI
    private lateinit var scoreManager: ScoreManager // TODO use DI
    private lateinit var gameTimer: GameTimer // TODO use DI

    private var _word = MutableLiveData<String>()
    private var _score = MutableLiveData<Int>()
    private var _timeLeft = MutableLiveData<Int>()
    private var _endGameEvent = MutableLiveData<Boolean>()
    private var _buzzEvent = MutableLiveData<BuzzType>()

    val word: LiveData<String> get() = _word
    val score: LiveData<Int> get() = _score
    val timeLeft: LiveData<Int> get() = _timeLeft
    val endGameEvent: LiveData<Boolean> get() = _endGameEvent
    val buzzEvent: LiveData<BuzzType> get() = _buzzEvent

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
        _buzzEvent.value = BuzzType.CORRECT
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
        _endGameEvent.value = false
    }

    fun onGameEndCompleted() {
        _endGameEvent.value = false
    }

    fun onBuzzComplete() {
        _buzzEvent.value = BuzzType.NO_BUZZ
    }

    fun getScore(): Int {
        return score.value!!
    }

    override fun onCleared() {
        super.onCleared()
        gameTimer.stopGameTimer()
    }

    override fun onNextSecond(timeLeftInSeconds: Int) {
        _timeLeft.value = timeLeftInSeconds
        if (timeLeftInSeconds <= PANIC_START_TIME_IN_SECONDS) _buzzEvent.value =
            BuzzType.COUNTDOWN_PANIC
    }

    override fun onGameEnd() {
        _endGameEvent.value = true
        _timeLeft.value = NO_TIME_LEFT_IN_SECONDS
        _buzzEvent.value = BuzzType.GAME_OVER
    }

    companion object {
        private const val NO_TIME_LEFT_IN_SECONDS = 0
        private const val PANIC_START_TIME_IN_SECONDS = 10
    }
}