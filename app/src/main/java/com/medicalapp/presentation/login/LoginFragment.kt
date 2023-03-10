package com.medicalapp.presentation.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.medicalapp.R
import com.medicalapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(), LoginHandler {

    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.handler = this
    }

    override fun loginButtonClicked() {
        if (!TextUtils.isEmpty(binding.idEdtUserName.text)) {
            val bundle = bundleOf("username" to binding.idEdtUserName.text.toString())
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment, bundle)

        } else
            Toast.makeText(
                this@LoginFragment.context,
                getString(R.string.name_validation),
                Toast.LENGTH_LONG
            ).show()
    }
}