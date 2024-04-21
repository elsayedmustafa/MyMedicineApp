package com.example.mymedicineapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MedicineApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}