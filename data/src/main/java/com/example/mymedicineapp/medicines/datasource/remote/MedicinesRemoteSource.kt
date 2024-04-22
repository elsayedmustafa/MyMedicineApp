package com.example.mymedicineapp.medicines.datasource.remote

import com.example.mymedicineapp.medicine.model.MedicineDomainModel

interface MedicinesRemoteSource {
    suspend fun getMedicines(): List<MedicineDomainModel>
}
