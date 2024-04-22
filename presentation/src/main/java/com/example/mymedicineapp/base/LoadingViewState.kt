package com.example.mymedicineapp.base

sealed class LoadingViewState {
    object ShowLoadingViewState : LoadingViewState()
    object HideLoadingViewState : LoadingViewState()
}
