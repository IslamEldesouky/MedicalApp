package com.medicalapp

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
        val medicalDataResponse = Utils().getMockMedicalDataResponse()
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
        val medicalDataResponse = Utils().getMockMedicalDataResponse()
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