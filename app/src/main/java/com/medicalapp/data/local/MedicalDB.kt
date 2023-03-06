package com.medicalapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.medicalapp.domain.entity.MedicalDataResponse

@Database(
    entities = [MedicalDataResponse::class],
    version = DBConfig.Constants.DB_VERSION,
    exportSchema = false
)
abstract class MedicalDB : RoomDatabase() {

    abstract fun medicalDao(): MedicalDataDAO
}