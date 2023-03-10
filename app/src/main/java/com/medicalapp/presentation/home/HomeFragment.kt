package com.medicalapp.presentation.home

import PrescriptionAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.R
import com.medicalapp.databinding.FragmentHomeBinding
import com.medicalapp.domain.entity.MedicalDataResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), PrescriptionAdapter.ItemSelected, HomeHandler {

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

        binding.viewModel = viewModel
        binding.handler = this
        viewModel.getData()
        binding.progressBar.visibility = View.VISIBLE
        binding.empty.visibility = View.GONE
        val prescriptionAdapter = PrescriptionAdapter(this)
        val rvDrug: RecyclerView = binding.parentRecyclerview
        val linearLayoutManager = LinearLayoutManager(this@HomeFragment.context)
        rvDrug.layoutManager = linearLayoutManager

        val username = arguments?.getString("username")
        if (username != null) {
            viewModel.userName = username
        }

        lifecycleScope.launch() {
            binding.empty.visibility = View.GONE
            viewModel.medicalData.collect() {
                if (it == null) {
                    binding.empty.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                    binding.empty.visibility = View.GONE
                    prescriptionAdapter.submitList(
                        it.problems.get(0).diabetes.get(0).medications.get(0).medicationsClasses.get(0).className
                    )
                    rvDrug.adapter = prescriptionAdapter
                }
            }
        }
    }

    override fun itemSelected(searchItem: MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName) {}

    override fun drugSelected(drugItem: MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName.AssociatedDrug) {
        val bundle = bundleOf("name" to drugItem.name,"dose" to drugItem.dose, "strength" to drugItem.strength)
        findNavController().navigate(R.id.action_homeFragment_to_homeDetailsFragment,bundle)
    }

    override fun logOut() {
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
    }
}