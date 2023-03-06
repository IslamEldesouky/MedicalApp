package com.medicalapp.data.remote

import com.medicalapp.domain.entity.MedicalDataResponse
import retrofit2.http.GET

interface APIService {

    @GET(APIConfig.Constants.API_ENDPOINT)
    suspend fun getMedicalData(): MedicalDataResponse
}