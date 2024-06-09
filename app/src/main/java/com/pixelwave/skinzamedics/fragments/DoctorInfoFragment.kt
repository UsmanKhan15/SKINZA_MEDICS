package com.pixelwave.skinzamedics.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.pixelwave.skinzamedics.SharedViewModel
import com.pixelwave.skinzamedics.databinding.FragmentDoctorInfoBinding

class DoctorInfoFragment : Fragment() {
    private val binding by lazy {
        FragmentDoctorInfoBinding.inflate(layoutInflater)
    }
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Observe the selected doctor
        sharedViewModel.selectedDoctor.observe(viewLifecycleOwner) { doctor ->
            binding.doctorName.text = doctor.doctorName
//            binding.doctorExpertise.text = doctor.doctorExpertise
            binding.doctorAvailabilityTime.text = doctor.doctorAvailabilityTime
            binding.doctorFee.text = doctor.doctorFee
            binding.doctorQualification.text = doctor.doctorQualification
            binding.doctorExperience.text = doctor.doctorExperience
            binding.doctorRole.text = doctor.doctorRole
            binding.doctorLocation.text = doctor.doctorLocation
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