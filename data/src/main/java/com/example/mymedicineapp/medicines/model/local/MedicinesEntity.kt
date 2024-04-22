package com.example.mymedicineapp.medicines.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines_tbl"  )
data class MedicinesEntity(
    @PrimaryKey(autoGenerate = true)
    val  id: Int=0,
    val  medicineName: String,val  medicineDose: String, val medicineStrength: String
)
