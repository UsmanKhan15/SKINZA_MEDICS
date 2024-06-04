package com.pixelwave.skinzamedics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pixelwave.skinzamedics.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.constraintDiagnose.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_registerCaseFragment)
        }
        binding.constraintFindDoctor.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_findADoctorFragment)
        }
        return binding.root
    }
}