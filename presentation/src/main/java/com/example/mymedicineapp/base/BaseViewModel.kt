package com.example.mymedicineapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymedicineapp.exception.NoInternetException
import com.example.mymedicineapp.exception.SessionTimeOutException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

open class BaseViewModel() : ViewModel() {

    protected var mLoadingState =
        MutableSharedFlow<LoadingViewState>()
    val loadingState = mLoadingState.asSharedFlow()

    private val mFailureViewStateError = MutableSharedFlow<FailureViewState>()
    val failureViewStateError = mFailureViewStateError.asSharedFlow()

    protected val baseExceptionHandlerHandler = CoroutineExceptionHandler { _, e ->
        viewModelScope.launch(Dispatchers.Main) {
            onFailure(e)
        }
    }

    private suspend fun onShowLoading() {
        mLoadingState.emit(LoadingViewState.ShowLoadingViewState)
    }

    private suspend fun onHideLoading() {
        mLoadingState.emit(LoadingViewState.HideLoadingViewState)
    }

    suspend fun <T : Any> enqueueApiCall(apiCall: suspend () -> T): T {
        onShowLoading()
        val result = apiCall.invoke()
        onHideLoading()
        return result
    }

    private suspend fun onFailure(throwable: Throwable) {
        when (throwable) {
            is SessionTimeOutException -> {
                mFailureViewStateError.emit(FailureViewState.SessionTimeOutViewState)
            }
            is NoInternetException -> {
                mFailureViewStateError.emit(FailureViewState.NoInternetViewState)
            }
            else -> {
                mFailureViewStateError.emit(FailureViewState.UnExpectedViewState)
            }
        }.apply { mLoadingState.emit(LoadingViewState.HideLoadingViewState) }
    }

    fun resetFailureViewState() {
        viewModelScope.launch {
            mFailureViewStateError.emit(FailureViewState.NoFailure)
        }
    }
}
