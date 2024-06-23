package com.pixelwave.skinzamedics

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pixelwave.skinzamedics.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {
    private val binding by lazy { FragmentHelpBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.imageView25.setOnClickListener {
            //open whatsapp chat with suggested number on click
            openWhatsApp()
        }
        binding.textView19.setOnClickListener {
            //open whatsapp chat with suggested number on click
            openWhatsApp()
        }
        binding.imageView24.setOnClickListener {
            //open instagram on click
            openInstagram()
        }
        binding.textView20.setOnClickListener {
            //open instagram on click
            openInstagram()
        }
        binding.imageView2.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        binding.textView8.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        return binding.root
    }

    fun openWhatsApp() {
        val phoneNumber = "+923479301617"
        val packageManager = context?.packageManager
        val intent = Intent(Intent.ACTION_VIEW)

        try {
            val url = "https://wa.me/$phoneNumber"
            intent.setPackage("com.whatsapp")
            intent.data = Uri.parse(url)

            if (intent.resolveActivity(packageManager!!) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(context, "WhatsApp is not installed on your device", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
        }
    }

    fun openInstagram() {
        val profileUrl = "https://instagram.com/your_profile_name"
        val packageManager = context?.packageManager
        val intent = Intent(Intent.ACTION_VIEW)

        try {
            // First, try to open the profile in the Instagram app
            if (packageManager != null) {
                if (intent.resolveActivity(packageManager) != null) {
                    intent.setPackage("com.instagram.android")
                    intent.data = Uri.parse(profileUrl)
                    startActivity(intent)
                } else {
                    // If Instagram app is not installed, open in browser
                    intent.data = Uri.parse(profileUrl)
                    startActivity(intent)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
        }
    }

}