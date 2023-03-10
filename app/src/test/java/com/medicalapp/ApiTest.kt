package com.medicalapp

import com.medicalapp.domain.entity.MedicalDataResponse
import com.medicalapp.domain.usecase.GetMedicalDataUseCase
import com.medicalapp.presentation.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class ApiTest {

    private lateinit var viewModel: HomeViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(testDispatcher)

    @Mock
    private lateinit var useCase: GetMedicalDataUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = HomeViewModel(useCase)
    }

    @Test
    fun getMedicalDataFromAPIandCheckName() = testScope.runTest {
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
                    "JOHN"
                )
            )
        Mockito.lenient().`when`(useCase.getMedicalData()).thenReturn(medicalDataResponse)
        coroutineScope {
            assert(
                useCase.getMedicalData().problems.get(0).diabetes.get(0).medications.get(0).medicationsClasses.get(
                    0
                ).className.get(0).associatedDrug.get(0).name == "ASPIRIN"
            )
        }
    }

    @Test
    fun getMedicalDataFromAPIandCheckStregth() = testScope.runTest {
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
        Mockito.lenient().`when`(useCase.getMedicalData()).thenReturn(medicalDataResponse)
        coroutineScope {
            assert(
                useCase.getMedicalData().problems.get(0).diabetes.get(0).medications.get(0).medicationsClasses.get(
                    0
                ).className.get(0).associatedDrug.get(0).strength == "500mg"
            )
        }
    }
}