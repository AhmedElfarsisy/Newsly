package com.iti.elfarsisy.mad41.newsly.features.signUp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.iti.elfarsisy.mad41.newsly.R
import com.iti.elfarsisy.mad41.newsly.data.datasource.local.auth.data.userRepo.UserRepo
import com.iti.elfarsisy.mad41.newsly.databinding.FragmentSignUpBinding
import com.iti.elfarsisy.mad41.newsly.features.signUp.viewmodel.SignUpViewModel


class SignUpFragment : Fragment() {


    private lateinit var binding: FragmentSignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.bind(
            inflater.inflate(
                R.layout.fragment_sign_up,
                container,
                false
            )
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Initialize sign up view model
        signUpViewModel = ViewModelProvider(
            this,
            SignUpViewModel.SingUpViewModelFactory(UserRepo.getUserRepoInstance(requireActivity().applicationContext))
        ).get(SignUpViewModel::class.java)


        // Add signUp action
        binding.btnSignUp.setOnClickListener {

            signUpViewModel.navigateToHome()
        }

        // Observe signUp live data
        signUpViewModel.signUpFlagLiveData.observe(requireActivity()) {

            val email = binding.emailTV.text.toString()
            val password = binding.password.text.toString()
            val confirmPassword = binding.confirmPassword.text.toString()

            signUpViewModel.validateUserSignUpData(email, password, confirmPassword)

        }




    }


}