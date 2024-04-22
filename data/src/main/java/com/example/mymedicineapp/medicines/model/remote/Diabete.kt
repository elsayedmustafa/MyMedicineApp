package com.example.mymedicineapp.medicines.model.remote


import com.google.gson.annotations.SerializedName

data class Diabete(
    @SerializedName("labs")
    val labs: List<Lab>,
    @SerializedName("medications")
    val medications: List<Medication>
)