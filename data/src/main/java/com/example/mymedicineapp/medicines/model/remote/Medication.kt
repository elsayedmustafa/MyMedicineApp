package com.example.mymedicineapp.medicines.model.remote


import com.google.gson.annotations.SerializedName

data class Medication(
    @SerializedName("medicationsClasses")
    val medicationsClasses: List<MedicationsClasse>
)