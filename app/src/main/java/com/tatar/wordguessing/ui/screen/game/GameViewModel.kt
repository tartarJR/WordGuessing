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

    private var _wordLiveData = MutableLiveData<String>()
    private var _scoreLiveData = MutableLiveData<Score>()

    val wordLiveData: LiveData<String> get() = _wordLiveData
    val scoreLiveData: LiveData<Score> get() = _scoreLiveData

    init {
        initWord()
        initScore()
    }

    fun onCorrectAnswer() {
        scoreManager.increaseScore()
        _scoreLiveData.value = scoreManager.getScore()
        updateWordIfWordsListNotEmpty()
    }

    fun onSkipWord() {
        scoreManager.decreaseScore()
        _scoreLiveData.value = scoreManager.getScore()
        updateWordIfWordsListNotEmpty()
    }

    private fun updateWordIfWordsListNotEmpty() {
        if (wordProvider.isWordListNotEmpty())
            _wordLiveData.value = wordProvider.getNextWord()
    }

    private fun initWord() {
        wordProvider = WordProvider()
        wordProvider.refreshList()
        _wordLiveData.value = wordProvider.getNextWord()
    }

    private fun initScore() {
        scoreManager = ScoreManager()
        scoreManager.provideScore()
        _scoreLiveData.value = scoreManager.getScore()
    }
}