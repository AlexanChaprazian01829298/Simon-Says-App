package com.example.bigbrainthegame

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.bigbrainthegame.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)

        val view = binding.root
        var colorUpdate: String = ""
        var themeUpdate: String = ""

        binding.changeButton.setOnClickListener {
            if (binding.red.isChecked) {
                colorUpdate = "Red"
            } else if (binding.green.isChecked) {
                colorUpdate = "Green"
            } else if (binding.blue.isChecked) {
                colorUpdate = "Blue"
            }

            if (binding.tokyo.isChecked) {
                themeUpdate = "tokyo"
            }
            else if (binding.london.isChecked) {
                themeUpdate = "london"
            }
            else if (binding.vegas.isChecked) {
                themeUpdate = "vegas"
            }
            else if (binding.beijing.isChecked) {
                themeUpdate = "beijing"
            }
            onClick(view, colorUpdate, themeUpdate)

        }
        return view
    }

    private fun onClick(view: View, colorUpdate: String, themeUpdate: String) {
        val action = SettingsFragmentDirections.actionSettingsFragmentToSimonSaysFragment(colorUpdate, themeUpdate)
        view.findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}