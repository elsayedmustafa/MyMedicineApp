package com.example.mymedicineapp.base

sealed class FailureViewState {
    object SessionTimeOutViewState : FailureViewState()
    object NoInternetViewState : FailureViewState()
    object UnExpectedViewState : FailureViewState()
    object NoFailure : FailureViewState()
}
