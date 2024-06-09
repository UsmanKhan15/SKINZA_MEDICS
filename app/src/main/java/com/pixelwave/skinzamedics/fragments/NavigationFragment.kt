package com.pixelwave.skinzamedics.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.pixelwave.skinzamedics.databinding.FragmentNavigationBinding

class NavigationFragment : Fragment() {
    private val binding by lazy { FragmentNavigationBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.imageView21.setOnClickListener {
            // Logout
            FirebaseAuth.getInstance().signOut()
            requireActivity().finishAffinity()
        }
        return binding.root
    }
}