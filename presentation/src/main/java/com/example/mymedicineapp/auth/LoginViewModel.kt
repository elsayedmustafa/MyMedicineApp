package com.example.mymedicineapp.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.mymedicineapp.auth.usecase.LoginUseCase
import com.example.mymedicineapp.base.BaseViewModel
import com.example.mymedicineapp.base.IoDispatcher
import com.example.mymedicineapp.base.LoadingViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : BaseViewModel() {
    private val mLoginState = mutableStateOf(false)
    val loginState get() =  mLoginState

    fun login(userName: String) {
        viewModelScope.launch(baseExceptionHandlerHandler + defaultDispatcher) {
            try {
                enqueueApiCall { loginUseCase.invoke(userName) }
                mLoginState.value=true
            } finally {
                mLoadingState.emit(LoadingViewState.HideLoadingViewState)
            }
        }
    }

}
