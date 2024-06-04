package com.pixelwave.skinzamedics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pixelwave.skinzamedics.databinding.FragmentEnvironmentalBinding

class EnvironmentalFragment : Fragment() {
    private val binding by lazy {
        FragmentEnvironmentalBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.textView22.setOnClickListener {
            findNavController().navigate(R.id.action_environmentalFragment_to_familySkinFragment)
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}