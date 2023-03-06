package com.medicalapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.medicalapp.data.local.Queries.SELECT_MEDICAL_DATA_FROM_TABLE
import com.medicalapp.domain.entity.MedicalDataResponse

@Dao
interface MedicalDataDAO {

    @Insert
    suspend fun saveMedicalData(medicalDataResponse: MedicalDataResponse)

    @Query("$SELECT_MEDICAL_DATA_FROM_TABLE")
    suspend fun getMedicalData(): MedicalDataResponse
}