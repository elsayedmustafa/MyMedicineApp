package com.example.mymedicineapp.medicines.datasource.remote


import com.example.mymedicineapp.medicine.model.MedicineDomainModel
import com.example.mymedicineapp.medicines.mapper.MedicinesDomainMapper
import com.example.mymedicineapp.medicines.remote.MedicinesApi
import com.example.mymedicineapp.network.NetworkRouter
import java.lang.Exception
import javax.inject.Inject

class MedicinesRemoteDataSource @Inject constructor(
    private val medicineApi: MedicinesApi,
    private val networkRouter: NetworkRouter,
    private val medicinesDomainMapper: MedicinesDomainMapper
) : MedicinesRemoteSource {

    override suspend fun getMedicines(): List<MedicineDomainModel> {

        try {
            val result = networkRouter.invokeApi { medicineApi.getMedicines() }
            return medicinesDomainMapper.mapMedicinesApiModelToMedicinesDomainModel(result)
        } catch (ex: Exception) {
            throw ex
        }
    }

}
