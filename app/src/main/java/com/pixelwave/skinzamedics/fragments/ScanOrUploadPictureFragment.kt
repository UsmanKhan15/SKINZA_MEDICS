package com.pixelwave.skinzamedics.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.fragment.findNavController
import com.pixelwave.skinzamedics.R
import com.pixelwave.skinzamedics.databinding.FragmentScanOrUploadPictureBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


// ScanOrUploadPictureFragment.kt

class ScanOrUploadPictureFragment : Fragment() {
    private val binding by lazy {
        FragmentScanOrUploadPictureBinding.inflate(layoutInflater)
    }
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_IMAGE_SELECT = 2
    private val REQUEST_CAMERA_PERMISSION = 3
    private lateinit var imageUri: Uri
    private lateinit var currentPhotoPath: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.textView22.setOnClickListener {
            findNavController().navigate(R.id.action_scanOrUploadPictureFragment_to_durationFragment)
        }

        binding.textView10.setOnClickListener {
            dispatchTakePictureIntent()
        }

        binding.imageView15.setOnClickListener {
            dispatchSelectPictureIntent()
        }
        binding.imageView2.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        binding.textView8.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        return binding.root
    }

    private fun dispatchTakePictureIntent() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
        } else {
            launchCamera()
        }
    }

    private fun launchCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                photoFile?.also {
                    imageUri = FileProvider.getUriForFile(
                        requireContext(),
                        "your.package.name.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    private fun createImageFile(): File {
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File =
            requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission granted, proceed with the camera intent
                launchCamera()
            } else {
                // Permission denied, show an explanation to the user
                Toast.makeText(
                    requireContext(),
                    "Camera permission is required to take pictures",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun dispatchSelectPictureIntent() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_SELECT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    displayImage(imageUri)
                }

                REQUEST_IMAGE_SELECT -> {
                    data?.data?.let { uri ->
                        displayImage(uri)
                    }
                }
            }
        }
    }

    private fun displayImage(uri: Uri) {
        val imageView = ImageView(requireContext()).apply {
            layoutParams = LinearLayout.LayoutParams(0, 200).apply {
                weight = 1f
            }
            setImageURI(uri)
        }
        binding.imageContainer.addView(imageView)
    }
}
