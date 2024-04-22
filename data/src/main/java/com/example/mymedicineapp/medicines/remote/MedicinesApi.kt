package com.example.mymedicineapp.medicines.remote

import com.example.mymedicineapp.medicines.model.remote.MedicinesApiModel
import retrofit2.Response
import retrofit2.http.GET

interface MedicinesApi {
    companion object {
        private const val MEDICINES_PATH = "fac10a75-b7b7-474a-8ed4-39e0bd83359f"
    }
    @GET(MEDICINES_PATH)
    suspend fun getMedicines(): Response<MedicinesApiModel>

}
