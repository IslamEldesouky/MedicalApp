package com.medicalapp

import com.medicalapp.domain.entity.MedicalDataResponse

class Utils {

    fun getMockMedicalDataResponse(): MedicalDataResponse {
        val drug: List<MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName.AssociatedDrug> =
            listOf(
                MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName.AssociatedDrug(
                    1,
                    "JOHN",
                    "ASPIRIN",
                    "500mg"
                )
            )
        val className: List<MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName> =
            listOf(
                MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName(
                    1,
                    drug,
                    drug
                )
            )
        val medicationClasses: List<MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass> =
            listOf(
                MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass(
                    className,
                    className
                )
            )
        val medication: List<MedicalDataResponse.Problem.Diabetes.Medication> =
            listOf(MedicalDataResponse.Problem.Diabetes.Medication(medicationClasses))
        val lab: List<MedicalDataResponse.Problem.Diabetes.Lab> = ArrayList()
        val asthma: List<MedicalDataResponse.Problem.Asthma> = ArrayList()
        val diabetes: List<MedicalDataResponse.Problem.Diabetes> =
            listOf(MedicalDataResponse.Problem.Diabetes(lab, medication))
        val problem: List<MedicalDataResponse.Problem> =
            listOf(MedicalDataResponse.Problem(asthma, diabetes))
        return MedicalDataResponse(problem, 1)
    }
}