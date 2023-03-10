package com.medicalapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.medicalapp.data.local.MedicalDB
import com.medicalapp.data.local.MedicalDataDAO
import com.medicalapp.domain.entity.MedicalDataResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class QueryTestExample {
    private lateinit var medicalDataDAO: MedicalDataDAO
    lateinit var db: MedicalDB

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MedicalDB::class.java).allowMainThreadQueries()
            .build()
        medicalDataDAO = db!!.medicalDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db!!.close()
    }

    @Test
    fun insertAndReadMedicalData() = testScope.runTest {
        val medicalDataResponse = Utils().getMockMedicalDataResponse()
        medicalDataDAO.saveMedicalData(medicalDataResponse)
        assert(medicalDataDAO!!.getMedicalData().problems.get(0).diabetes.get(0).medications[0].medicationsClasses[0].className[0].associatedDrug[0].name == "ASPIRIN")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertAndReadMedicalDataStrength() = testScope.runTest {
        val medicalDataResponse = Utils().getMockMedicalDataResponse()
        medicalDataDAO.saveMedicalData(medicalDataResponse)
        coroutineScope {
            assert(medicalDataDAO!!.getMedicalData().problems.get(0).diabetes.get(0).medications[0].medicationsClasses[0].className[0].associatedDrug[0].name == "ASPIRIN")
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertAndReadMedicalDataDose() = testScope.runTest {
        val medicalDataResponse = Utils().getMockMedicalDataResponse()
        medicalDataDAO.saveMedicalData(medicalDataResponse)
        assert(medicalDataDAO!!.getMedicalData().problems.get(0).diabetes.get(0).medications[0].medicationsClasses[0].className[0].associatedDrug[0].name == "ASPIRIN")
    }
}