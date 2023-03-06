package com.medicalapp.domain.repo

import com.medicalapp.domain.entity.MedicalDataResponse

interface MedicalDataRepository {

    suspend fun getMedicalData(): MedicalDataResponse
}