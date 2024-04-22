package com.example.mymedicineapp.medicines.model.remote


import com.google.gson.annotations.SerializedName

data class Problem(
    @SerializedName("Asthma")
    val asthma: List<Asthma>,
    @SerializedName("Diabetes")
    val diabetes: List<Diabete>
)