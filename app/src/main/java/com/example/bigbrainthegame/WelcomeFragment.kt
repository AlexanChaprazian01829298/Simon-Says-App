package com.example.bigbrainthegame


import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import com.example.bigbrainthegame.databinding.FragmentWelcomeBinding



class WelcomeFragment : Fragment() {
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!
    var highestScore = "0"
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var mMediaPlayer: MediaPlayer? = null
        mMediaPlayer = MediaPlayer.create(context, R.raw.gamemusic)
        mMediaPlayer!!.isLooping = true
        mMediaPlayer.start()

        _binding = FragmentWelcomeBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val background = WelcomeFragmentArgs.fromBundle(requireArguments()).background


        if(background == "london") {
            binding.gifView.setImageResource(R.drawable.london)
        }

        binding.soundOff.setOnClickListener{
            mMediaPlayer!!.isLooping = false
            mMediaPlayer.pause()
        }
        binding.soundOn.setOnClickListener{
            mMediaPlayer!!.isLooping = true
            mMediaPlayer.start()
        }

        binding.button.setOnClickListener {
            onClick(view)
        }

        sharedPreferences = requireActivity().getSharedPreferences("Highest_Score", Context.MODE_PRIVATE)
        highestScore = sharedPreferences.getInt("Highest_Score", 0).toString()
        binding.highScore.text = "High Score: $highestScore"
        return view
    }


    private fun onClick(view: View) {
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToSimonSaysFragment()
        view.findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


