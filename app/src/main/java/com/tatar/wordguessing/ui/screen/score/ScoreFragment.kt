package com.tatar.wordguessing.ui.screen.score


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.tatar.wordguessing.R
import com.tatar.wordguessing.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {

    private lateinit var binding: FragmentScoreBinding

    private lateinit var viewModelFactory: ScoreViewModelFactory
    private lateinit var viewModel: ScoreViewModel

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
        val finalScore = ScoreFragmentArgs.fromBundle(requireArguments()).score
        viewModelFactory = ScoreViewModelFactory(finalScore)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ScoreViewModel::class.java)
    }

    private fun setBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_score,
            container,
            false
        )
    }

    private fun initViews() {
        binding.playAgainBtn.setOnClickListener { restartGame() }
    }

    private fun setObservations() {
        viewModel.score.observe(this, Observer { score -> binding.finalScore = score })
    }

    private fun restartGame() {
        findNavController().navigate(ScoreFragmentDirections.actionPlayAgain())
    }
}
