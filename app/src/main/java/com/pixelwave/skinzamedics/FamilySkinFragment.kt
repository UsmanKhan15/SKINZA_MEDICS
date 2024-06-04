package com.pixelwave.skinzamedics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pixelwave.skinzamedics.databinding.FragmentFamilySkinBinding


class FamilySkinFragment : Fragment() {
   private val binding by lazy {
       FragmentFamilySkinBinding.inflate(layoutInflater)
   }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.textView22.setOnClickListener {
            findNavController().navigate(R.id.action_familySkinFragment_to_facialConditionFragment)
        }
        return binding.root
    }
}