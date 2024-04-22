package com.example.mymedicineapp.medicines.model.remote


import com.google.gson.annotations.SerializedName

data class MedicinesApiModel(
    @SerializedName("problems")
    val problems: List<Problem>
)