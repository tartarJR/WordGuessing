package com.tatar.wordguessing.ui.screen.game


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

import com.tatar.wordguessing.R
import com.tatar.wordguessing.databinding.FragmentGameBinding
import com.tatar.wordguessing.databinding.FragmentPreGameBinding


class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentGameBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )

        binding.correctBtn.setOnClickListener {
            
        }

        binding.skipBtn.setOnClickListener {

        }

        return binding.root
    }
}
