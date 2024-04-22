package com.example.mymedicineapp.medicines

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.banquemisr.visitapp.presentation.base.IoDispatcher
import com.example.mymedicineapp.base.BaseViewModel
import com.example.mymedicineapp.base.LoadingViewState
import com.example.mymedicineapp.medicine.model.MedicineDomainModel
import com.example.mymedicineapp.medicine.usecase.MedicinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicinesViewModel @Inject constructor(
    private val medicinesUseCase: MedicinesUseCase,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : BaseViewModel() {
    private val mMedicinesListState = mutableStateOf(emptyList<MedicineDomainModel>())
    val medicinesListState get() =  mMedicinesListState

    fun getMedicines() {
        viewModelScope.launch(baseExceptionHandlerHandler + defaultDispatcher) {
            try {
                val result = enqueueApiCall { medicinesUseCase.invoke() }
                mMedicinesListState.value=result
            } finally {
                mLoadingState.emit(LoadingViewState.HideLoadingViewState)
            }
        }
    }

}
