package com.example.mymedicineapp.medicines.mapper

import com.example.mymedicineapp.medicine.model.MedicineDomainModel
import com.example.mymedicineapp.medicines.model.local.MedicinesEntity
import javax.inject.Inject

class EntityMedicinesDomainMapper @Inject constructor() {
    fun mapMedicinesEntityModelToMedicinesDomainModel(medicinesEntityList: List<MedicinesEntity>?): ArrayList<MedicineDomainModel> {
        val medicineDomainModelList= arrayListOf<MedicineDomainModel>()
        medicinesEntityList?.forEach {
                                    medicineDomainModelList.add(
                                        MedicineDomainModel(
                                            it.medicineName,
                                            it.medicineDose,
                                            it.medicineStrength
                                        )
                                    )

            }
        return medicineDomainModelList
    }

    fun mapMedicinesDomainModelToMedicinesEntityModel(medicinesEntityList: List<MedicineDomainModel>?): ArrayList<MedicinesEntity> {
        val medicinesDomainModelList= arrayListOf<MedicinesEntity>()
        medicinesEntityList?.forEach {
            medicinesDomainModelList.add(
                MedicinesEntity(
                    medicineName = it.medicineName,
                    medicineDose = it.medicineDose,
                    medicineStrength = it.medicineStrength
                )
            )

        }
        return medicinesDomainModelList
    }
}
