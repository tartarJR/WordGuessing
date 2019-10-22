package com.tatar.wordguessing.ui.screen.game


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tatar.wordguessing.R
import com.tatar.wordguessing.data.ScoreManager
import com.tatar.wordguessing.data.WordProvider
import com.tatar.wordguessing.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    private lateinit var wordProvider: WordProvider
    private lateinit var scoreManager: ScoreManager

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initWords()
        initScore()
        setBindings(inflater, container)
        initViews()

        return binding.root
    }

    private fun initWords() {
        wordProvider = WordProvider()
        wordProvider.refreshList()
    }

    private fun initScore() {
        scoreManager = ScoreManager()
        scoreManager.provideScore()
    }

    private fun setBindings(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )

        binding.score = scoreManager.getScore()
        binding.word = wordProvider.getWord()
    }

    private fun initViews() {
        binding.correctBtn.setOnClickListener { onCorrectAnswer() }
        binding.skipBtn.setOnClickListener { onSkipWord() }
    }

    private fun onCorrectAnswer() {
        if (wordProvider.isWordListEmpty()) {
            navigateToScoreFragment()
            return
        }

        displayNextWord()
        displayIncreasedScore()
    }

    private fun displayIncreasedScore() {
        scoreManager.increaseScore()
        binding.score = scoreManager.getScore()
    }

    private fun onSkipWord() {
        if (wordProvider.isWordListEmpty()) {
            navigateToScoreFragment()
            return
        }

        displayNextWord()
        displayDecreasedScore()
    }

    private fun displayDecreasedScore() {
        scoreManager.decreaseScore()
        binding.score = scoreManager.getScore()
    }

    private fun displayNextWord() {
        binding.word = wordProvider.getWord()
    }

    private fun navigateToScoreFragment() {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToScoreFragment(
                binding.score!!.finalScore,
                binding.score!!.completionTimeInSeconds
            )
        )
    }
}
