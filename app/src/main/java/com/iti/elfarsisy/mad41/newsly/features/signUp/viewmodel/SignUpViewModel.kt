package com.iti.elfarsisy.mad41.newsly.features.signUp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.iti.elfarsisy.mad41.newsly.data.datasource.local.auth.data.userRepo.UserRepoInterface
import com.iti.elfarsisy.mad41.newsly.data.model.User
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class SignUpViewModel(private val userRepo: UserRepoInterface?) : ViewModel() {

    private var _signUpFlagLiveData = MutableLiveData<ValueWrapper<Boolean>>()
    var signUpFlagLiveData = _signUpFlagLiveData

    private var _inValidEmailAddressLiveData = MutableLiveData<ValueWrapper<Boolean>>()
    var inValidEmailAddressLiveData = _inValidEmailAddressLiveData

    private var _passwordMismatchLiveData = MutableLiveData<ValueWrapper<Boolean>>()
    var passwordMismatchLiveData = _passwordMismatchLiveData

    private var _fillAllFieldsLiveData = MutableLiveData<ValueWrapper<Boolean>>()
    private var fillAllFieldsLiveData = _fillAllFieldsLiveData


    fun signUp(user: User) {

        viewModelScope.launch {

            userRepo?.signUp(user)
        }

    }

    fun navigateToHome() {

        signUpFlagLiveData.postValue(ValueWrapper(true))
    }

    fun validateUserSignUpData(email: String, password: String, confirmPassword: String) {

        // Validate email
        val emailRegex = """a([bc]+)d?""".toRegex()
        val matchResult = emailRegex.matchEntire("abbccbbd")

        // Validate password
        if (password != confirmPassword) {

            _passwordMismatchLiveData.postValue(ValueWrapper(true))

        } else if(email.isBlank() || password.isBlank() || confirmPassword.isBlank()){


        }




    }

    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    class SingUpViewModelFactory(private val userRepo: UserRepoInterface?) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SignUpViewModel(userRepo) as T
        }
    }
}


