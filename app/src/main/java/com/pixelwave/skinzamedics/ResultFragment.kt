package com.pixelwave.skinzamedics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pixelwave.skinzamedics.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private val binding by lazy { FragmentResultBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.textView22.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_moreInfoResult)
        }
        binding.imageView2.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        binding.textView8.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        return binding.root
    }
}