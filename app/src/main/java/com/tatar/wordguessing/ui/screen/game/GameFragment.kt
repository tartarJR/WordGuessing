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
import com.tatar.wordguessing.helper.Buzzer
import com.tatar.wordguessing.helper.Buzzer.BuzzType


class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel
    private lateinit var buzzer: Buzzer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setViewModel()
        setBinding(inflater, container)
        setEventObservations()

        buzzer = Buzzer(requireActivity())

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

        binding.gameViewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setEventObservations() {
        viewModel.endGameEvent.observe(this, Observer { hasEnded -> if (hasEnded) finishGame() })
        viewModel.buzzEvent.observe(
            this,
            Observer { buzzType ->
                if (buzzType != BuzzType.NO_BUZZ) {
                    buzzer.buzz(buzzType)
                    viewModel.onBuzzComplete()
                }
            }
        )
    }

    private fun finishGame() {
        findNavController(this).navigate(GameFragmentDirections.actionGoToScoreFragment(viewModel.score.value!!))
        viewModel.onGameEndCompleted()
    }
}
