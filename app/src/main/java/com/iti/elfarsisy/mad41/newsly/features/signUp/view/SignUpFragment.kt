package com.iti.elfarsisy.mad41.newsly.features.signUp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.iti.elfarsisy.mad41.newsly.R
import com.iti.elfarsisy.mad41.newsly.data.datasource.local.auth.data.userRepo.UserRepo
import com.iti.elfarsisy.mad41.newsly.databinding.FragmentSignUpBinding
import com.iti.elfarsisy.mad41.newsly.features.signUp.viewmodel.SignUpViewModel
import com.iti.elfarsisy.mad41.newsly.util.UIHelper


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

        // Observe invalid email live data
        signUpViewModel.inValidEmailAddressLiveData.observe(requireActivity()) {

           UIHelper.showError("Invalid email address", requireView())

        }

        // Observe password mismatch live data
        signUpViewModel.passwordMismatchLiveData.observe(requireActivity()) {

           UIHelper.showError("Password mismatch", requireView())

        }

        // Observe invalid email live data
        signUpViewModel.fillAllFieldsLiveData.observe(requireActivity()) {
            if (it.getContent() != null) {

                UIHelper.showError("Please fill all fields", requireView())

            }

        }

        // Observe add user live data
        signUpViewModel.addUserLiveData.observe(requireActivity()) { user ->

            if (user != null) {

                signUpViewModel.signUp(user)

            }
        }

        // Open login fragment
        binding.tvopenLogin.setOnClickListener {

            requireActivity().supportFragmentManager.popBackStack()

        }

        // Observe home user live data
        signUpViewModel.homeUserLiveData.observe(requireActivity()) {

            Log.i("TAG", "onViewCreated: aft7 ya semsem")
            Log.i("TAG", "onViewCreated: home user live data ${it.email}")
        }

        // Observe user not found
        signUpViewModel.userAlreadyExistLiveData.observe(requireActivity()) {


            it.getContent()?.let {
                UIHelper.showError("User Exist, Dear try again", requireView())
            }


        }

    }


}