package com.medicalapp.data.repo

import android.util.Log
import com.medicalapp.data.local.MedicalDataDAO
import com.medicalapp.data.remote.APIService
import com.medicalapp.domain.entity.MedicalDataResponse
import com.medicalapp.domain.repo.MedicalDataRepository

class MedicalRepositoryImpl(
    private val apiService: APIService,
    private val medicalDataDAO: MedicalDataDAO
) : MedicalDataRepository {
    override suspend fun getMedicalData(): MedicalDataResponse {
        return getMedicalDataDb()
    }

    suspend fun getMedicalDataAPI(): MedicalDataResponse {
        lateinit var medicalDataResponse: MedicalDataResponse
        try {
            val response = apiService.getMedicalData()
            medicalDataResponse = response
        } catch (e: java.lang.Exception) {
            Log.i("Err", e.message.toString())
        }
        return medicalDataResponse
    }

    suspend fun getMedicalDataDb(): MedicalDataResponse {
        lateinit var medicalDataResponse: MedicalDataResponse
        try {
            val response = medicalDataDAO.getMedicalData()
            if (response != null) {
                medicalDataResponse = response
                return medicalDataResponse
            } else {
                medicalDataResponse = getMedicalDataAPI()
                medicalDataDAO.saveMedicalData(medicalDataResponse)
            }
        } catch (e: Exception) {
            Log.i("Err", e.message.toString())
        }
        return medicalDataResponse
    }
}