package com.tatar.wordguessing.ui.screen.pregame


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.tatar.wordguessing.R
import com.tatar.wordguessing.databinding.FragmentPreGameBinding


class PreGameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPreGameBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_pre_game,
            container,
            false
        )

        return binding.root
    }
}
