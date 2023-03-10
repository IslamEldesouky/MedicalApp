package com.medicalapp

import com.medicalapp.data.repo.FakeRepo
import com.medicalapp.domain.entity.MedicalDataResponse
import com.medicalapp.domain.usecase.GetMedicalDataUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RepoTest {

    lateinit var useCase: GetMedicalDataUseCase
    lateinit var medicalDataResponse: MedicalDataResponse

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        val fakeRepo = FakeRepo()
        useCase = GetMedicalDataUseCase(fakeRepo)
    }

    @Test
    fun checkRepoReturnsMedicalDataResponseForDrugName() = testScope.runTest {
        medicalDataResponse = useCase.getMedicalData()
        assert(
            useCase.getMedicalData().problems.get(0).diabetes.get(0).medications.get(0).medicationsClasses.get(
                0
            ).className.get(0).associatedDrug.get(0).name == "ASPIRIN"
        )
    }

    @Test
    fun checkRepoReturnsMedicalDataResponseForDrugStrength() = testScope.runTest {
        medicalDataResponse = useCase.getMedicalData()
        assert(
            useCase.getMedicalData().problems.get(0).diabetes.get(0).medications.get(0).medicationsClasses.get(
                0
            ).className.get(0).associatedDrug.get(0).strength == "500mg"
        )
    }
}