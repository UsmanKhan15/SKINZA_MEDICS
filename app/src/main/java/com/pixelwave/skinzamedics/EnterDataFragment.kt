package com.pixelwave.skinzamedics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.pixelwave.skinzamedics.databinding.FragmentEnterDataBinding

class EnterDataFragment : Fragment() {
    private val binding by lazy { FragmentEnterDataBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.textView13.setOnClickListener {
            // Getting references to the input fields and calendar view
            val userName = binding.editTextText.text.toString()
            val day = binding.calendarView.dayOfMonth
            val month = binding.calendarView.month + 1 // Month is 0-based
            val year = binding.calendarView.year
            val dateOfBirth = "$day/$month/$year"

            val user = FirebaseAuth.getInstance().currentUser
            val userEmail = user?.email?.replace(".", ",") // Firebase keys should not contain "."

            if (user != null) {
                // Reference to the Firebase Realtime Database
                val database = FirebaseDatabase.getInstance()
                val userRef = database.getReference("users").child(userEmail!!)

                // Creating a map to hold the user data
                val userData = mapOf(
                    "userName" to userName,
                    "dateOfBirth" to dateOfBirth
                )
                userRef.setValue(userData)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            findNavController().navigate(R.id.action_enterDataFragment_to_homeFragment)
                            Toast.makeText(requireContext(), "Data uploaded successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), "Failed to upload data: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        return binding.root
    }
}