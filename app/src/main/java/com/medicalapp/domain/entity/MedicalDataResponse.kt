package com.medicalapp.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.medicalapp.data.local.Converters
import com.medicalapp.data.local.DBConfig.Constants.MEDICAL_TABLE_NAME

@Entity(tableName = MEDICAL_TABLE_NAME)
data class MedicalDataResponse(
    @field:TypeConverters(Converters::class)
    val problems: List<Problem>,
    @PrimaryKey(autoGenerate = true)
    val id: Int
)  {
    data class Problem(
        @SerializedName("Asthma")
        val asthma: List<Asthma>,
        @SerializedName("Diabetes")
        val diabetes: List<Diabetes>

    ) {
        data class Diabetes(
            val labs: List<Lab>,
            val medications: List<Medication>
        ) {
            data class Lab(
                val missingField: String
            )

            data class Medication(
                val medicationsClasses: List<MedicationsClass>
            ) {
                data class MedicationsClass(
                    val className: List<ClassName>,
                    val className2: List<ClassName>
                ) {
                    data class ClassName(
                        val id: Int,
                        val associatedDrug: List<AssociatedDrug>,
                        @SerializedName("associatedDrug#2")
                        val associatedDrug2: List<AssociatedDrug>
                    ) {
                        data class AssociatedDrug(
                            val id: Int,
                            val dose: String,
                            val name: String,
                            val strength: String
                        )
                    }
                }
            }
        }

        class Asthma
    }
}