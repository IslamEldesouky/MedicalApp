package com.medicalapp.presentation.home

import DrugAdapter
import PrescriptionAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.databinding.FragmentHomeBinding
import com.medicalapp.domain.entity.MedicalDataResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), PrescriptionAdapter.ItemSelected {

    private val viewModel: HomeViewModel by viewModels()
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCurrentWeatherData()
        val prescriptionAdapter = PrescriptionAdapter(this)
        val rvDrug: RecyclerView = binding.parentRecyclerview
        val linearLayoutManager = LinearLayoutManager(this@HomeFragment.context)
        rvDrug.layoutManager = linearLayoutManager


        lifecycleScope.launch() {
            viewModel.medicalData.collect() {
                Log.d("MEDICAL", "HELLO")
                Log.d(
                    "MEDICAL",
                    it?.problems?.get(0)?.diabetes?.get(0)?.medications?.get(0)?.medicationsClasses?.get(
                        0
                    )?.className?.get(0)?.associatedDrug?.size.toString()
                )
                if (it == null) {
                    Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show()
                } else {
                    prescriptionAdapter.submitList(
                        it.problems.get(0).diabetes.get(0).medications.get(0).medicationsClasses.get(0).className
                    )
                    rvDrug.adapter = prescriptionAdapter
                }
            }
        }
    }

    override fun itemSelected(searchItem: MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName) {
        TODO("Not yet implemented")
    }

    override fun drugSelected(drugItem: MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName.AssociatedDrug) {
        TODO("Not yet implemented")
    }
}