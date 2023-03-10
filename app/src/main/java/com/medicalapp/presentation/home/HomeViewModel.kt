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
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getMedicalDataUseCase: GetMedicalDataUseCase) : ViewModel() {

    private val _medicalData: MutableStateFlow<MedicalDataResponse?> = MutableStateFlow(null)
    val medicalData: StateFlow<MedicalDataResponse?> = _medicalData
    var userName: String = ""

    fun getData() {
        viewModelScope.launch {
            try {
                Log.d("MEDICAL", "HELLO")
                _medicalData.value = getMedicalDataUseCase.getMedicalData()
            } catch (e: java.lang.Exception) {
                Log.e("ERROR", e.message.toString())
            }
        }
    }

    fun getCurrentDate(): String? {
        val formatter = SimpleDateFormat("dd MMM yyyy")
        val date = Date()
        val current = formatter.format(date)
        return current
    }
}