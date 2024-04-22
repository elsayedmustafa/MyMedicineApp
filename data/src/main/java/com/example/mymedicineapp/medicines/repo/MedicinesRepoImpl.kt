package com.example.mymedicineapp.medicines.repo

import android.util.Log
import com.example.mymedicineapp.medicine.model.MedicineDomainModel
import com.example.mymedicineapp.medicine.repo.MedicinesRepo
import com.example.mymedicineapp.medicines.datasource.local.MedicinesLocalSource
import com.example.mymedicineapp.medicines.datasource.remote.MedicinesRemoteSource
import javax.inject.Inject

class MedicinesRepoImpl @Inject  constructor(private val medicinesRemoteSource: MedicinesRemoteSource,
                                             private val medicinesLocalSource: MedicinesLocalSource) :MedicinesRepo{
    override suspend fun getMedicines(): List<MedicineDomainModel> {
        try {
            var result = medicinesRemoteSource.getMedicines()
            if (result.isEmpty()) {
                result = medicinesLocalSource.getMedicines()
            } else {
                medicinesLocalSource.insertMedicines(result)
            }
            return result
        }catch (e:Exception){
            val result = medicinesLocalSource.getMedicines()
            if (result.isEmpty())
                throw e
            else
                return result
        }
    }
}