package com.example.mymedicineapp.medicine.usecase

import com.example.mymedicineapp.exception.ApiException
import com.example.mymedicineapp.exception.NoInternetException
import com.example.mymedicineapp.exception.SessionTimeOutException
import com.example.mymedicineapp.exception.UnexpectedException
import com.example.mymedicineapp.medicine.model.MedicineDomainModel
import com.example.mymedicineapp.medicine.repo.MedicinesRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import org.junit.Assert.assertEquals
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MedicinesUseCaseTest {

    private lateinit var medicinesUseCase: MedicinesUseCase
    @Mock
    lateinit var medicinesRepo: MedicinesRepo
    @Before
    fun setup() {
        medicinesUseCase = MedicinesUseCase(medicinesRepo)
    }

    @Test
    fun `test Medicines given Unit when invoke Medicines then return listof MedicineModel`() {
        runTest {
            whenever(medicinesRepo.getMedicines()).doReturn(MEDICINE_MODEL_LIST)
            val result = medicinesUseCase.invoke()
            verify(medicinesRepo, times(1)).getMedicines()
            assertEquals(MEDICINE_MODEL_LIST, result)

        }
    }

    @Test
    fun `test Medicines given Unit when invoke Medicines then return empty list`() {
        runTest {
            whenever(medicinesRepo.getMedicines()).doReturn(emptyList())
            val result = medicinesUseCase.invoke()
            verify(medicinesRepo, times(1)).getMedicines()
            assertEquals(emptyList<MedicineDomainModel>(), result)

        }
    }

    @Test(expected = ApiException::class)
    fun `test Medicines given Unit when invoke Medicines then throw ApiException`() {
        runTest {
            whenever(medicinesRepo.getMedicines()).doAnswer {
                throw ApiException()
            }
            medicinesUseCase.invoke()
            verify(medicinesRepo, times(1)).getMedicines()

        }
    }

    @Test(expected = NoInternetException::class)
    fun `test Medicines given Unit when invoke Medicines then throw NoInternetException`() {
        runTest {
            whenever(medicinesRepo.getMedicines()).doAnswer {
                throw NoInternetException()
            }
            medicinesUseCase.invoke()
            verify(medicinesRepo, times(1)).getMedicines()

        }
    }

    @Test(expected = SessionTimeOutException::class)
    fun `test Medicines given Unit when invoke Medicines then throw SessionTimeOutException`() {
        runTest {
            whenever(medicinesRepo.getMedicines()).doAnswer {
                throw SessionTimeOutException()
            }
            medicinesUseCase.invoke()
            verify(medicinesRepo, times(1)).getMedicines()

        }
    }

    @Test(expected = UnexpectedException::class)
    fun `test Medicines given Unit when invoke Medicines then throw UnexpectedException`() {
        runTest {
            whenever(medicinesRepo.getMedicines()).doAnswer {
                throw UnexpectedException()
            }
            medicinesUseCase.invoke()
            verify(medicinesRepo, times(1)).getMedicines()

        }
    }

}