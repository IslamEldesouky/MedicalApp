package com.medicalapp.domain.entity

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.medicalapp.data.local.DBConfig.Constants.MEDICAL_TABLE_NAME

@Entity(tableName = MEDICAL_TABLE_NAME)
data class MedicalDataResponse(
    val problems: List<Problem>
) {
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
                val medicationsClasses: List<MedicationsClasse>
            ) {
                data class MedicationsClasse(
                    val className: List<ClassName>,
                    val className2: List<ClassName>
                ) {
                    data class ClassName(
                        val associatedDrug: List<AssociatedDrug>,
                        @SerializedName("associatedDrug#2")
                        val associatedDrug2: List<AssociatedDrug>
                    ) {
                        data class AssociatedDrug(
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