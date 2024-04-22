package com.example.mymedicineapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mymedicineapp.medicines.model.local.MedicinesEntity

@Database(entities = [MedicinesEntity::class], version = 1, exportSchema = false)
abstract class MedicinesAppDatabase : RoomDatabase() {
    abstract fun medicinesAppDao(): MedicinesAppDao
}
