package com.example.mymedicineapp.medicines.datasource.local


import com.example.mymedicineapp.database.MedicinesAppDao
import com.example.mymedicineapp.medicine.model.MedicineDomainModel
import com.example.mymedicineapp.medicines.mapper.EntityMedicinesDomainMapper
import javax.inject.Inject

class MedicinesLocalDataSource @Inject constructor(
    private val entityMedicinesDomainMapper: EntityMedicinesDomainMapper,
    private val medicinesAppDao: MedicinesAppDao
) : MedicinesLocalSource {
    override suspend fun insertMedicines(medicinesList: List<MedicineDomainModel>) {
        medicinesAppDao.insertMedicines(entityMedicinesDomainMapper.mapMedicinesDomainModelToMedicinesEntityModel(medicinesList))
    }

    override suspend fun getMedicines(): List<MedicineDomainModel> {
         return entityMedicinesDomainMapper.mapMedicinesEntityModelToMedicinesDomainModel(medicinesAppDao.getMedicines())

    }

}
