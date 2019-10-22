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
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        setBinding(inflater, container)
        setObservations()
        initViews()

        return binding.root
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
        viewModel.scoreLiveData.observe(this, Observer { newScore -> binding.score = newScore })
        viewModel.wordLiveData.observe(this, Observer { newWord -> binding.word = newWord })
    }

    private fun correctBtnClick() {
        viewModel.onCorrectAnswer()
    }

    private fun skipBtnClick() {
        viewModel.onSkipWord()
    }

    private fun navigateToScoreFragment() {
        findNavController(this).navigate(
            GameFragmentDirections.actionGameFragmentToScoreFragment(
                viewModel.scoreLiveData.value!!.finalScore,
                viewModel.scoreLiveData.value!!.completionTimeInSeconds
            )
        )
    }
}
