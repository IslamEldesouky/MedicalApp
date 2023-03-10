package com.medicalapp.data.repo

import com.medicalapp.domain.entity.MedicalDataResponse
import com.medicalapp.domain.repo.MedicalDataRepository

class FakeRepo : MedicalDataRepository {
    override suspend fun getMedicalData(): MedicalDataResponse {
        val problem: List<MedicalDataResponse.Problem> = ArrayList()
        val medicalDataResponse: MedicalDataResponse = MedicalDataResponse(problem, 1)
        val diabetes: List<MedicalDataResponse.Problem.Diabetes> = ArrayList()
        val asthma: List<MedicalDataResponse.Problem.Asthma> = ArrayList()
        val lab: List<MedicalDataResponse.Problem.Diabetes.Lab> = ArrayList()
        val medication: List<MedicalDataResponse.Problem.Diabetes.Medication> = ArrayList()
        val medicationClasses: List<MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass> =
            ArrayList()
        val className: List<MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName> =
            ArrayList()
        val drug: List<MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName.AssociatedDrug> =
            ArrayList()
        medicalDataResponse.problems = listOf(MedicalDataResponse.Problem(asthma, diabetes))
        medicalDataResponse.problems[0].diabetes =
            listOf(MedicalDataResponse.Problem.Diabetes(lab, medication))
        medicalDataResponse.problems[0].diabetes[0].medications =
            listOf(MedicalDataResponse.Problem.Diabetes.Medication(medicationClasses))
        medicalDataResponse.problems[0].diabetes[0].medications[0].medicationsClasses = listOf(
            MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass(
                className,
                className
            )
        )
        medicalDataResponse.problems[0].diabetes[0].medications[0].medicationsClasses[0].className =
            listOf(
                MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName(
                    1,
                    drug,
                    drug
                )
            )
        medicalDataResponse.problems[0].diabetes[0].medications[0].medicationsClasses[0].className[0].associatedDrug =
            listOf(
                MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName.AssociatedDrug(
                    1,
                    "JOHN",
                    "ASPIRIN",
                    "500mg"
                )
            )
        return medicalDataResponse
    }
}