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
                    "JOHN",
                    "JOHN"
                )
            )
        medicalDataDAO.saveMedicalData(medicalDataResponse)
        assert(medicalDataDAO!!.getMedicalData().problems.get(0).diabetes.get(0).medications[0].medicationsClasses[0].className[0].associatedDrug[0].name == "JOHN")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertAndReadMedicalDataStrength() = testScope.runTest {
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
                    "JOHN",
                    "JOHN"
                )
            )
        medicalDataDAO.saveMedicalData(medicalDataResponse)
        coroutineScope {
            assert(medicalDataDAO!!.getMedicalData().problems.get(0).diabetes.get(0).medications[0].medicationsClasses[0].className[0].associatedDrug[0].name == "JOHN")
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertAndReadMedicalDataDose() = testScope.runTest {
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
                    "JOHN",
                    "JOHN"
                )
            )
        medicalDataDAO.saveMedicalData(medicalDataResponse)
        assert(medicalDataDAO!!.getMedicalData().problems.get(0).diabetes.get(0).medications[0].medicationsClasses[0].className[0].associatedDrug[0].name == "JOHN")
    }
}