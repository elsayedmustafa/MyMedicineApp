package com.example.mymedicineapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.example.mymedicineapp.auth.navhost.MedicineAppMainNavHost
import com.example.mymedicineapp.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider {
                MedicineAppMainNavHost()
            }
        }
    }
}
