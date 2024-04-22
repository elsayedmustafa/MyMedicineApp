package com.example.mymedicineapp.medicines.datasource.local

import com.example.mymedicineapp.medicine.model.MedicineDomainModel

interface MedicinesLocalSource {
    suspend fun insertMedicines(medicinesList:List<MedicineDomainModel>)
    suspend fun getMedicines(): List<MedicineDomainModel>
}
