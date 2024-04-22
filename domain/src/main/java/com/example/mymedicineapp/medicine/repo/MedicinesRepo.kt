package com.example.mymedicineapp.medicine.repo

import com.example.mymedicineapp.medicine.model.MedicineDomainModel

interface MedicinesRepo {
    suspend fun getMedicines():List<MedicineDomainModel>
}