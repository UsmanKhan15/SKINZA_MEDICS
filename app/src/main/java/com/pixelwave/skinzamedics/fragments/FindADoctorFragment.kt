package com.pixelwave.skinzamedics.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.pixelwave.skinzamedics.DoctorAdapter
import com.pixelwave.skinzamedics.R
import com.pixelwave.skinzamedics.SharedViewModel
import com.pixelwave.skinzamedics.databinding.FragmentFindADoctorBinding
import com.pixelwave.skinzamedics.dataclass.Doctor

class FindADoctorFragment : Fragment() {
    private val binding by lazy {
        FragmentFindADoctorBinding.inflate(layoutInflater)
    }

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var doctorAdapter: DoctorAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.imageView2.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        binding.textView8.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchDoctorsFromFirebase { doctors ->
            doctorAdapter = DoctorAdapter(doctors) { doctor ->
                // Store the selected doctor in the ViewModel
                sharedViewModel.selectDoctor(doctor)
                // Navigate to DoctorInfoFragment
                findNavController().navigate(R.id.action_findADoctorFragment_to_doctorInfoFragment)
            }
            binding.recyclerViewDoctors.adapter = doctorAdapter
            binding.recyclerViewDoctors.layoutManager = LinearLayoutManager(context)
        }
    }
}

fun fetchDoctorsFromFirebase(onDoctorsFetched: (List<Doctor>) -> Unit) {
    val database = FirebaseDatabase.getInstance()
    val doctorsRef = database.getReference("doctors")

    doctorsRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val doctors = mutableListOf<Doctor>()
            for (snapshot in dataSnapshot.children) {
                val doctor = snapshot.getValue(Doctor::class.java)
                doctor?.let { doctors.add(it) }
            }
            onDoctorsFetched(doctors)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Handle possible errors.
        }
    })
}