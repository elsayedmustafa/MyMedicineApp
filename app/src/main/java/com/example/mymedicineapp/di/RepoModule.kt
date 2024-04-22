package com.example.mymedicineapp.di

import com.example.mymedicineapp.medicine.repo.MedicinesRepo
import com.example.mymedicineapp.medicines.repo.MedicinesRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun provideMedicinesRepository(impl: MedicinesRepoImpl): MedicinesRepo

    }
