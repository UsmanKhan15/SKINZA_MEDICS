package com.pixelwave.skinzamedics.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.pixelwave.skinzamedics.R
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
        binding.imageView2.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        binding.textView8.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        binding.imageView19.setOnClickListener {
            openLink(requireContext(), "https://sites.google.com/view/skinzamedics-privacy-policy/home")
        }
        // Getting the current user's email
        val user = FirebaseAuth.getInstance().currentUser
        val userEmail = user?.email?.replace(".", ",")

        if (userEmail != null) {
            val database = FirebaseDatabase.getInstance()
            val userRef = database.getReference("users").child(userEmail)

            // Fetching the data
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Getting the user data
                    val userName = snapshot.child("userName").getValue(String::class.java)
                    val dateOfBirth = snapshot.child("dateOfBirth").getValue(String::class.java)

                    // Updating the UI
                    binding.textView34.text = userName
//                    binding.tvDateOfBirth.text = dateOfBirth
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), "Failed to load data: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
        binding.imageView20.setOnClickListener {
            findNavController().navigate(R.id.action_navigationFragment_to_helpFragment)
        }
        return binding.root
    }

    fun openLink(context: Context, link: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(link)
        context.startActivity(intent)
    }
}