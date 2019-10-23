package com.tatar.wordguessing.ui.screen.game


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.tatar.wordguessing.R
import com.tatar.wordguessing.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setViewModel()
        setBinding(inflater, container)
        setObservations()
        initViews()

        return binding.root
    }

    private fun setViewModel() {
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
    }

    private fun setBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )
    }

    private fun initViews() {
        binding.correctBtn.setOnClickListener { correctBtnClick() }
        binding.skipBtn.setOnClickListener { skipBtnClick() }
    }

    private fun setObservations() {
        viewModel.score.observe(this, Observer { newScore -> binding.score = newScore })
        viewModel.word.observe(this, Observer { newWord -> binding.word = newWord })
        viewModel.timeLeft.observe(this, Observer { timeLeft -> binding.timeLeft = timeLeft })
        viewModel.eventEndGame.observe(this, Observer { hasEnded -> if (hasEnded) finishGame() })
    }

    private fun correctBtnClick() {
        viewModel.onCorrectAnswer()
    }

    private fun skipBtnClick() {
        viewModel.onSkipWord()
    }

    private fun finishGame() {
        findNavController(this).navigate(GameFragmentDirections.actionGoToScoreFragment(viewModel.getScoreString()))
        viewModel.onGameEndCompleted()
    }
}
