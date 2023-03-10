package com.medicalapp.presentation.homeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.medicalapp.databinding.FragmentHomeDetailsBinding

class HomeDetailsFragment : Fragment() {

    lateinit var binding: FragmentHomeDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = arguments?.getString("name")
        val dose = arguments?.getString("dose")
        val strength = arguments?.getString("strength")
        binding.drugItemName.text = name
        binding.drugItemDose.text = dose
        binding.drugItemStrength.text = strength
    }
}