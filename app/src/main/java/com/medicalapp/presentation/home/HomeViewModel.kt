package com.medicalapp.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medicalapp.domain.entity.MedicalDataResponse
import com.medicalapp.domain.usecase.GetMedicalDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getMedicalDataUseCase: GetMedicalDataUseCase) : ViewModel() {

    private val _medicalData : MutableStateFlow<MedicalDataResponse?> = MutableStateFlow(null)
    val medicalData : StateFlow<MedicalDataResponse?> = _medicalData

    fun getCurrentWeatherData(){
        viewModelScope.launch{
            try {
                Log.d("MEDICAL","HELLO")
                _medicalData.value = getMedicalDataUseCase.getMedicalData()
            }catch (e:java.lang.Exception){
                Log.e("ERROR",e.message.toString())
            }
        }
    }
}