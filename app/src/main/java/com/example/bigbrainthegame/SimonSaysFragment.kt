package com.example.bigbrainthegame

import SimonSaysViewModel
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.bigbrainthegame.databinding.FragmentSimonSaysBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SimonSaysFragment : Fragment() {
    private var _binding: FragmentSimonSaysBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: SimonSaysViewModel
    //private var buttonColor = Color.argb(255, 160, 32, 240)
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSimonSaysBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(SimonSaysViewModel::class.java)

        when(SimonSaysFragmentArgs.fromBundle(requireArguments()).message){

            "Red" -> viewModel.buttonColor = Color.argb(100,251,24,24)
            "Green" -> viewModel.buttonColor = Color.argb(100,9,145,27)
            "Blue" -> viewModel.buttonColor = Color.argb(100,17,39,237)
        }

        when(SimonSaysFragmentArgs.fromBundle(requireArguments()).background1) {
            "london" -> binding.gifView.setImageResource(R.drawable.london)
            "tokyo" -> binding.gifView.setImageResource(R.drawable.tokyo)
            "vegas" -> binding.gifView.setImageResource(R.drawable.vegas)
            "beijing" -> binding.gifView.setImageResource(R.drawable.beijing)
            "hello" -> binding.gifView.setImageResource(R.drawable.beijing)
        }

        binding.q1.setOnClickListener {
            GlobalScope.launch { onClickFlash(binding.q1) }
            if(viewModel.isStarted) { onClick(view, 1) }
        }
        binding.q2.setOnClickListener {
            GlobalScope.launch { onClickFlash(binding.q2) }
            if(viewModel.isStarted) { onClick(view, 2) }
        }
        binding.q3.setOnClickListener {
            GlobalScope.launch { onClickFlash(binding.q3) }
            if(viewModel.isStarted) { onClick(view, 3) }
        }
        binding.q4.setOnClickListener {
            GlobalScope.launch { onClickFlash(binding.q4) }
            if(viewModel.isStarted) { onClick(view, 4) }
        }

        binding.beginButton.setOnClickListener {
            viewModel.start()

            GlobalScope.launch {
                flashColorSequence()
            }
        }

        sharedPreferences = requireActivity().getSharedPreferences("Highest_Score", Context.MODE_PRIVATE)
        return view
    }

    private suspend fun onClickFlash(gameButton: Button) {
        gameButton.setBackgroundColor(viewModel.buttonColor)
        delay(200L)
        gameButton.setBackgroundColor(viewModel.buttonOff)
    }

    private fun onClick(view: View, buttonNum: Int) {
        viewModel.onPressedButton(buttonNum)
        binding.rounds.text = "Round ${viewModel.roundNum}"
        GlobalScope.launch {
            if(viewModel.isUserDone) {
                delay(1000L)
                flashColorSequence()
            }
        }

        var highestScore = sharedPreferences.getInt("Highest_Score", 0)
        if(viewModel.score > highestScore) {
            val editor = sharedPreferences.edit()
            editor.putInt("Highest_Score", viewModel.score)
            editor.apply()
        }
        if(viewModel.isGameOver) {
            val action = SimonSaysFragmentDirections.actionSimonSaysFragmentToGameOverFragment(viewModel.score.toString())
            view.findNavController().navigate(action)
        }
    }
    private suspend fun flashColorSequence() {
        for(i in viewModel.sequence) {
            when (i) {
                1 -> {
                    delay(400L)
                    binding.q1.setBackgroundColor(viewModel.buttonColor)
                    delay(400L)
                    binding.q1.setBackgroundColor(viewModel.buttonOff)
                    delay(400L)
                }
                2 -> {
                    delay(400L)
                    binding.q2.setBackgroundColor(viewModel.buttonColor)
                    delay(400L)
                    binding.q2.setBackgroundColor(viewModel.buttonOff)
                    delay(400L)
                }
                3 -> {
                    delay(400L)
                    binding.q3.setBackgroundColor(viewModel.buttonColor)
                    delay(400L)
                    binding.q3.setBackgroundColor(viewModel.buttonOff)
                    delay(400L)
                }
                4 -> {
                    delay(400L)
                    binding.q4.setBackgroundColor(viewModel.buttonColor)
                    delay(400L)
                    binding.q4.setBackgroundColor(viewModel.buttonOff)
                    delay(400L)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}