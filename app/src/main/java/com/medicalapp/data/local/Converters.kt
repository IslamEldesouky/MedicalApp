package com.medicalapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.medicalapp.domain.entity.MedicalDataResponse

class Converters {
    @TypeConverter
    fun fromWeatherList(weather: List<MedicalDataResponse.Problem>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<MedicalDataResponse.Problem>>() {}.type
        return gson.toJson(weather, type)
    }

    @TypeConverter
    fun toWeatherList(json: String?): List<MedicalDataResponse.Problem>? {
        if (json != null && json.isEmpty()) return null

        val gson = Gson()
        val type = object : TypeToken<List<MedicalDataResponse.Problem>>() {}.type
        return gson.fromJson(json, type)
    }
}