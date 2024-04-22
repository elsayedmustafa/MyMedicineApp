package com.example.mymedicineapp.di

import com.example.mymedicineapp.medicines.datasource.local.MedicinesLocalDataSource
import com.example.mymedicineapp.medicines.datasource.local.MedicinesLocalSource
import com.example.mymedicineapp.medicines.datasource.remote.MedicinesRemoteDataSource
import com.example.mymedicineapp.medicines.datasource.remote.MedicinesRemoteSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun provideMedicinesRemoteDataSource(impl: MedicinesRemoteDataSource): MedicinesRemoteSource
    @Binds
    abstract fun provideMedicinesLocalDataSource(impl: MedicinesLocalDataSource): MedicinesLocalSource

}
