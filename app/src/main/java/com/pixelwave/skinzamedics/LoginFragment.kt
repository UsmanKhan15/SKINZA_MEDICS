package com.pixelwave.skinzamedics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pixelwave.skinzamedics.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.btnLogin.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        binding.btnSignUP.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
        return binding.root
    }
}