package com.example.mymedicineapp.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.mymedicineapp.R
import com.example.mymedicineapp.auth.navhost.MainNavHostDestinations
import com.example.mymedicineapp.base.dialogs.ErrorDialog
import com.example.mymedicineapp.base.dialogs.LoadingDialog

@Composable
fun BaseComposableScreen(
    navController: NavController,
    viewModel: BaseViewModel,
    content: @Composable () -> Unit
) {
    val failureViewState = viewModel.failureViewStateError.collectAsState(initial = FailureViewState.NoFailure)
    val loadingState = viewModel.loadingState.collectAsState(initial = LoadingViewState.HideLoadingViewState)
    val showDialog = remember { mutableStateOf(false) }

    LoadingDialog(loadingState.value == LoadingViewState.ShowLoadingViewState)

    when (failureViewState.value) {
        FailureViewState.NoInternetViewState -> {
            showDialog.value = true
            ErrorDialog(htmlMessage = stringResource(id = R.string.no_internet_exception), shouldDismiss = showDialog, onDismiss = {
                showDialog.value = false
                viewModel.resetFailureViewState()
            })
        }
        FailureViewState.SessionTimeOutViewState -> {
            showDialog.value = true
            ErrorDialog(htmlMessage = stringResource(id = R.string.timeout_exception), shouldDismiss = showDialog, failureViewState = failureViewState.value, onDismiss = {
                showDialog.value = false
                viewModel.resetFailureViewState()
            }, onClicked = {
                navController.navigate(MainNavHostDestinations.LOGIN_SCREEN.value) {
                    popUpTo(MainNavHostDestinations.MEDICINES_SCREEN.value) { inclusive = true }
                }
            })
        }
        FailureViewState.UnExpectedViewState -> {
            showDialog.value = true
            ErrorDialog(htmlMessage = stringResource(id = R.string.unexpected_exception), shouldDismiss = showDialog, onDismiss = {
                showDialog.value = false
                viewModel.resetFailureViewState()
            })
        }
        FailureViewState.NoFailure -> {
        }
    }

    content()
}
