package com.pixelwave.skinzamedics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.pixelwave.skinzamedics.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //fetch data from firebase and show on home screen
// Getting the current user's email
        val user = FirebaseAuth.getInstance().currentUser
        val userEmail = user?.email?.replace(".", ",")

        if (userEmail != null) {
            // Reference to the Firebase Realtime Database
            val database = FirebaseDatabase.getInstance()
            val userRef = database.getReference("users").child(userEmail)

            // Fetching the data
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Getting the user data
                    val userName = snapshot.child("userName").getValue(String::class.java)
                    val dateOfBirth = snapshot.child("dateOfBirth").getValue(String::class.java)

                    // Updating the UI
                    binding.textView15.text = userName
//                    binding.tvDateOfBirth.text = dateOfBirth
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), "Failed to load data: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
        binding.constraintDiagnose.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_registerCaseFragment)
        }
        binding.constraintFindDoctor.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_findADoctorFragment)
        }
        binding.imageView6.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_navigationFragment)
        }
        return binding.root
    }
}