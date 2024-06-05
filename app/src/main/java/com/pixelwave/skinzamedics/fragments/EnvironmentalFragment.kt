package com.pixelwave.skinzamedics.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pixelwave.skinzamedics.R
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
        setView()
        binding.option2d.setBackgroundResource(R.drawable.bg_disconnect)

        binding.option1d.setOnClickListener {
            setView()
            binding.option1d.setBackgroundResource(R.drawable.bg_disconnect)
        }
        binding.option2d.setOnClickListener {
            setView()
            binding.option2d.setBackgroundResource(R.drawable.bg_disconnect)
        }
        binding.option3d.setOnClickListener {
            setView()
            binding.option3d.setBackgroundResource(R.drawable.bg_disconnect)
        }
        binding.option4d.setOnClickListener {
            setView()
            binding.option4d.setBackgroundResource(R.drawable.bg_disconnect)
        }
        return binding.root
    }
    private fun setView() {
        binding.option1d.setBackgroundResource(R.drawable.bg_cancel)
        binding.option2d.setBackgroundResource(R.drawable.bg_cancel)
        binding.option3d.setBackgroundResource(R.drawable.bg_cancel)
        binding.option4d.setBackgroundResource(R.drawable.bg_cancel)
    }
}