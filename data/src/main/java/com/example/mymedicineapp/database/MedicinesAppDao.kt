package com.example.mymedicineapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mymedicineapp.medicines.model.local.MedicinesEntity

@Dao
interface MedicinesAppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicines(fieldEntity: List<MedicinesEntity>)

    @Query("SELECT * FROM medicines_tbl")
    suspend fun getMedicines(): List<MedicinesEntity>?


}
