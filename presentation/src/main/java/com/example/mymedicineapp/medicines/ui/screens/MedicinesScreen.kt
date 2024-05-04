package com.example.mymedicineapp.medicines.ui.screens

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mymedicineapp.R
import com.example.mymedicineapp.base.BaseComposableScreen
import com.example.mymedicineapp.medicine.model.MedicineDomainModel
import com.example.mymedicineapp.medicines.MedicinesViewModel
import com.example.mymedicineapp.medicines.ui.MedicineDetailsBottomSheetScreen
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicinesScreen(navController: NavController, userName: String) {
    val medicinesViewModel = hiltViewModel<MedicinesViewModel>()

    var isLoadedMedicines by rememberSaveable { mutableStateOf( false) }

    val showBottomSheet = remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val medicineDomainModelClicked = remember { mutableStateOf(MedicineDomainModel("","",""))}

    LaunchedEffect(isLoadedMedicines) {
        if (!isLoadedMedicines) {
            medicinesViewModel.getMedicines()
            isLoadedMedicines=true
        }
    }

    BaseComposableScreen(navController = navController, viewModel = medicinesViewModel) {
        MedicinesScreenMobile(userName , medicinesViewModel.medicinesListState.value ){
            showBottomSheet.value = true
            medicineDomainModelClicked.value=it
        }

    }

    if (showBottomSheet.value) {
        MedicineDetailsBottomSheetScreen(
            modalBottomSheetState = bottomSheetState,
            medicineDomainModelClicked = medicineDomainModelClicked.value,
            stringResource(id = R.string.details)
        ){
            showBottomSheet.value = false
        }
    }
}

@Composable
fun TextUserName(
    modifier: Modifier,
    username: String
) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.hello).plus(" ").plus(greetingBasedOnTime()).plus(" ").plus(username),
                style =  TextStyle(
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.greeting_text_color),
                )
            )


}
@Composable
private fun greetingBasedOnTime():String {
    val currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return when (currentTime) {
        in 6..11 -> stringResource(R.string.good_morning)
        in 12..16 -> stringResource(R.string.good_afternoon)
        in 17..20 -> stringResource(R.string.good_evening)
        else -> stringResource(R.string.good_night)
    }
}



