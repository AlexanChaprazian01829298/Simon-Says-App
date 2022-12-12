package com.example.bigbrainthegame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.bigbrainthegame.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {
    private var _binding: FragmentGameOverBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameOverBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val score = GameOverFragmentArgs.fromBundle(requireArguments()).score
        binding.finalScore.text = "Score: $score"

        binding.playAgain.setOnClickListener {

                view.findNavController().navigate(R.id.action_gameOverFragment_to_simonSaysFragment)
        }
        binding.Quit.setOnClickListener {

                view.findNavController().navigate(R.id.action_gameOverFragment_to_welcomeFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}