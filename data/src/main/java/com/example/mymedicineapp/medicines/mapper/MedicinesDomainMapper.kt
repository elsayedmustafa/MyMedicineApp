package com.example.mymedicineapp.medicines.mapper

import com.example.mymedicineapp.medicine.model.MedicineDomainModel
import com.example.mymedicineapp.medicines.model.remote.MedicinesApiModel
import javax.inject.Inject

class MedicinesDomainMapper @Inject constructor() {
    fun mapMedicinesApiModelToMedicinesDomainModel(medicinesApiModel: MedicinesApiModel?): ArrayList<MedicineDomainModel> {
        val medicineDomainModelList= arrayListOf<MedicineDomainModel>()
         medicinesApiModel?.problems?.forEach {
                it.diabetes.forEach {
                    it.medications.forEach {
                        it.medicationsClasses.forEach {
                            it.className.forEach {
                                it.associatedDrug.forEach {
                                    medicineDomainModelList.add(
                                        MedicineDomainModel(
                                            it.name,
                                            it.dose,
                                            it.strength
                                        )
                                    )
                                }
                                it.associatedDrug2.forEach {
                                    medicineDomainModelList.add(
                                        MedicineDomainModel(
                                            it.name,
                                            it.dose,
                                            it.strength
                                        )
                                    )
                                }
                            }
                            it.className2.forEach {
                                it.associatedDrug.forEach {
                                    medicineDomainModelList.add(
                                        MedicineDomainModel(
                                            it.name,
                                            it.dose,
                                            it.strength
                                        )
                                    )
                                }
                                it.associatedDrug2.forEach {
                                    medicineDomainModelList.add(
                                        MedicineDomainModel(
                                            it.name,
                                            it.dose,
                                            it.strength
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        return medicineDomainModelList
    }

}
