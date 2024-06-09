package com.pixelwave.skinzamedics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pixelwave.skinzamedics.dataclass.Doctor

class DoctorAdapter(
    private val doctors: List<Doctor>,
    private val onDoctorClick: (Doctor) -> Unit
) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_doctor, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctors[position]
        holder.bind(doctor, onDoctorClick)
    }

    override fun getItemCount(): Int = doctors.size

    class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val doctorName: TextView = itemView.findViewById(R.id.textViewDoctorName)
        private val doctorExpertise: TextView = itemView.findViewById(R.id.textViewDoctorExpertise)
        private val doctorQualification: TextView = itemView.findViewById(R.id.textViewDoctorQualification)

        fun bind(doctor: Doctor, onDoctorClick: (Doctor) -> Unit) {
            doctorName.text = doctor.doctorName
            doctorExpertise.text = doctor.doctorExpertise
            doctorQualification.text = doctor.doctorQualification

            itemView.setOnClickListener {
                onDoctorClick(doctor)
            }
        }
    }
}
