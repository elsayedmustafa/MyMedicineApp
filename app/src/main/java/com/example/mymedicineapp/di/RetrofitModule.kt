package com.example.mymedicineapp.di

import com.example.mymedicineapp.medicines.remote.MedicinesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideMedicinesApiService(retrofit: Retrofit): MedicinesApi {
        return retrofit.create(
            MedicinesApi::class.java
        )
    }
}
