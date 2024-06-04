package com.pixelwave.skinzamedics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pixelwave.skinzamedics.databinding.FragmentRegisterCaseBinding

class RegisterCaseFragment : Fragment() {
    private val binding by lazy {
        FragmentRegisterCaseBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.constraintLayout1.setOnClickListener {
            findNavController().navigate(R.id.action_registerCaseFragment_to_scanOrUploadPictureFragment)
        }
        return binding.root
    }
}