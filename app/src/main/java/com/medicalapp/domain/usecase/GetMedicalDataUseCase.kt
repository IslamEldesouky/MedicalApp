package com.medicalapp.domain.usecase

import com.medicalapp.domain.repo.MedicalDataRepository

class GetMedicalDataUseCase (private val medicalDataRepository: MedicalDataRepository) {

    suspend fun getMedicalData() = medicalDataRepository.getMedicalData()
}