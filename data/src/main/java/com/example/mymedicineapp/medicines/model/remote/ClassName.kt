package com.example.mymedicineapp.medicines.model.remote


import com.google.gson.annotations.SerializedName

data class ClassName(
    @SerializedName("associatedDrug")
    val associatedDrug: List<AssociatedDrug>,
    @SerializedName("associatedDrug#2")
    val associatedDrug2: List<AssociatedDrug>
)