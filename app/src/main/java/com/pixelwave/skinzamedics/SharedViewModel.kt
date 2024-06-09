package com.pixelwave.skinzamedics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pixelwave.skinzamedics.dataclass.Doctor

class SharedViewModel : ViewModel() {

    private val _selectedDoctor = MutableLiveData<Doctor>()
    val selectedDoctor: LiveData<Doctor> get() = _selectedDoctor

    fun selectDoctor(doctor: Doctor) {
        _selectedDoctor.value = doctor
    }
}
