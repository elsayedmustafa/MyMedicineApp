package com.example.mymedicineapp.medicines.model.remote


import com.google.gson.annotations.SerializedName

data class MedicationsClasse(
    @SerializedName("className")
    val className: List<ClassName>,
    @SerializedName("className2")
    val className2: List<ClassName>
)