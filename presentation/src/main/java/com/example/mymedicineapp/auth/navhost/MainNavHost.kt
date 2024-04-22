package com.example.mymedicineapp.auth.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mymedicineapp.auth.ui.screens.LoginScreen
import com.example.mymedicineapp.medicines.ui.screens.MedicinesScreen

enum class MainNavHostDestinations(val value: String) {
    LOGIN_SCREEN("LOGIN_SCREEN"),
    MEDICINES_SCREEN("MEDICINES_SCREEN"),
}

@Composable
fun MedicineAppMainNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainNavHostDestinations.LOGIN_SCREEN.value) {

        composable(MainNavHostDestinations.LOGIN_SCREEN.value
        )
        {
            LoginScreen(navController)
        }

        composable("${MainNavHostDestinations.MEDICINES_SCREEN.value}/{userName}",
            arguments = listOf(
                navArgument("userName") { type = NavType.StringType }
            )
        )
        {
            val data = it.arguments?.getString("userName") ?: ""
            MedicinesScreen(navController, data)
        }

    }
}
