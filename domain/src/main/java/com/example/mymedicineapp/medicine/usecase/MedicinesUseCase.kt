package com.example.mymedicineapp.medicine.usecase
import com.example.mymedicineapp.medicine.model.MedicineDomainModel
import com.example.mymedicineapp.medicine.repo.MedicinesRepo
import javax.inject.Inject


class MedicinesUseCase @Inject constructor(private val medicinesRepo: MedicinesRepo) {
    suspend operator fun invoke(): List<MedicineDomainModel> {
        return medicinesRepo.getMedicines()

    }
}
