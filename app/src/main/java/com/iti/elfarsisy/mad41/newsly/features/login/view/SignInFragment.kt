package com.iti.elfarsisy.mad41.newsly.features.login.view

import android.os.Bundle
import android.util.Log
import android.util.Patterns.*
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import com.iti.elfarsisy.mad41.newsly.R
import com.iti.elfarsisy.mad41.newsly.data.datasource.local.auth.data.userRepo.UserRepo
import com.iti.elfarsisy.mad41.newsly.data.model.UserPojo
import com.iti.elfarsisy.mad41.newsly.databinding.FragmentSigninBinding
import com.iti.elfarsisy.mad41.newsly.features.auth.login.SignInViewModel
import com.iti.elfarsisy.mad41.newsly.features.auth.login.SigninViewModelFactory
import com.iti.elfarsisy.mad41.newsly.features.signUp.view.SignUpFragment
import com.iti.elfarsisy.mad41.newsly.util.UIHelper

class SignInFragment : Fragment() {

    //region properties
    private lateinit var binding: FragmentSigninBinding

    private val viewModel: SignInViewModel by viewModels {
        SigninViewModelFactory(UserRepo.getUserRepoInstance(requireActivity().applicationContext)!!)
    }
    val TAG = "AlertsFragment"
    //endregion

    //region FragmentLifeCycle
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSigninBinding.inflate(inflater, container, false)
        initViews()
        setupViewModel()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    //endregion

    //region Methods
    private fun initViews() {
        binding.btnLogin.setOnClickListener {
            login()
        }
        binding.tvSignUp.setOnClickListener {

            displaySignUpFragment()
        }
    }

    private fun login() {
        if (binding.etemail.text.isNullOrEmpty() || binding.etpassword.text.isNullOrEmpty()) {
            UIHelper.showError("Please fill in username and password", requireView())
        } else if (!EMAIL_ADDRESS.matcher(binding.etemail.text.toString()).matches()) {
            UIHelper.showError("Please enter invalid email", requireView())
        } else {
            viewModel.signIn(
                UserPojo(
                    binding.etemail.text.toString(),
                    binding.etpassword.text.toString()
                )
            )
        }
    }




    private fun setupViewModel() {

        // Observe user live data
        viewModel.userLiveData.observe(requireActivity()) {

                Log.i(TAG, "setupViewModel: ${it.email}")

        }

        // Observe error live data
        viewModel.userNotFoundLiveData.observe(requireActivity()) {

            UIHelper.showError("User Not Found, Dear try again", requireView())
        }

        // Observe user list live data
//        viewModel.userListLiveData.observe(requireActivity()) { userList ->
//            val userEmails = userList.map { it.email }
//            val adapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_list_item_1, userEmails as MutableList<String>)
//            binding.etemail.setAdapter(adapter)
//        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    companion object {
        fun newInstance() =
            SignInFragment().apply {
            }
    }

    private fun displaySignUpFragment() {

        val signUpFragment = SignUpFragment()
        val mgr = requireActivity().supportFragmentManager
        val transaction = mgr.beginTransaction()
        transaction.add(R.id.homeFragmentContainer, signUpFragment, "signUp")
            .addToBackStack(null)
        transaction.commit()
    }
}