package com.medicalapp
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.medicalapp.data.local.MedicalDB
import com.medicalapp.data.local.MedicalDataDAO
import com.medicalapp.domain.entity.MedicalDataResponse
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class QueryTest {
    private lateinit var medicalDataDAO: MedicalDataDAO
    lateinit var db: MedicalDB

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MedicalDB::class.java).build()
        medicalDataDAO = db!!.medicalDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db!!.close()
    }

    //getting weird error...
    @Test
    suspend fun insertAndReadMedicalData() {
        val problem: List<MedicalDataResponse.Problem> = ArrayList()
        val medicalDataResponse: MedicalDataResponse = MedicalDataResponse(problem, 1)
        medicalDataResponse.problems.get(0).diabetes.get(0).medications[0].medicationsClasses[0].className[0].associatedDrug[0].name =
            "John"
        assert(medicalDataDAO!!.getMedicalData().problems.get(0).diabetes.get(0).medications[0].medicationsClasses[0].className[0].associatedDrug[0].name == "JOHN")
    } @Test
    suspend fun insertAndReadMedicalDataStrength() {
        val problem: List<MedicalDataResponse.Problem> = ArrayList()
        val medicalDataResponse: MedicalDataResponse = MedicalDataResponse(problem, 1)
        medicalDataResponse.problems.get(0).diabetes.get(0).medications[0].medicationsClasses[0].className[0].associatedDrug[0].strength =
            "John"
        assert(medicalDataDAO!!.getMedicalData().problems.get(0).diabetes.get(0).medications[0].medicationsClasses[0].className[0].associatedDrug[0].name == "JOHN")
    }

    @Test
    suspend fun insertAndReadMedicalDataDose() {
        val problem: List<MedicalDataResponse.Problem> = ArrayList()
        val medicalDataResponse: MedicalDataResponse = MedicalDataResponse(problem, 1)
        medicalDataResponse.problems.get(0).diabetes.get(0).medications[0].medicationsClasses[0].className[0].associatedDrug[0].dose =
            "John"
        assert(medicalDataDAO!!.getMedicalData().problems.get(0).diabetes.get(0).medications[0].medicationsClasses[0].className[0].associatedDrug[0].name == "JOHN")
    }
}
