package com.pixelwave.skinzamedics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pixelwave.skinzamedics.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {
    private val binding by lazy {
        FragmentSignupBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.btnSIgnUp.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_enterDataFragment)
        }

        binding.btnLoginSignUP.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
        return binding.root
    }
}