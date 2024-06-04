package com.pixelwave.skinzamedics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pixelwave.skinzamedics.databinding.FragmentEnterDataBinding

class EnterDataFragment : Fragment() {
    private val binding by lazy { FragmentEnterDataBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.textView13.setOnClickListener {
            findNavController().navigate(R.id.action_enterDataFragment_to_homeFragment)
        }
        return binding.root
    }
}