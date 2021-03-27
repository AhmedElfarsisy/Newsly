package com.iti.elfarsisy.mad41.newsly.features.signUp.viewmodel

import android.renderscript.Sampler
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.iti.elfarsisy.mad41.newsly.data.datasource.local.auth.data.userRepo.UserRepoInterface
import com.iti.elfarsisy.mad41.newsly.data.model.UserPojo
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class SignUpViewModel(private val userRepo: UserRepoInterface?) : ViewModel() {

    private var _signUpFlagLiveData = MutableLiveData<ValueWrapper<Boolean>>()
    val signUpFlagLiveData = _signUpFlagLiveData

    private var _inValidEmailAddressLiveData = MutableLiveData<ValueWrapper<Boolean>>()
    val inValidEmailAddressLiveData = _inValidEmailAddressLiveData

    private var _passwordMismatchLiveData = MutableLiveData<ValueWrapper<Boolean>>()
    val passwordMismatchLiveData = _passwordMismatchLiveData

    private var _fillAllFieldsLiveData = MutableLiveData<ValueWrapper<Boolean>>()
    val fillAllFieldsLiveData = _fillAllFieldsLiveData

    private var _addUserLiveData = MutableLiveData<UserPojo>()
    val addUserLiveData = _addUserLiveData

    private var _userAlreadyExistLiveData = MutableLiveData<ValueWrapper<Boolean>>()
    val userAlreadyExistLiveData = _userAlreadyExistLiveData

    private var _homeUserLiveDate = MutableLiveData<UserPojo>()
    val homeUserLiveData = _homeUserLiveDate


    fun navigateToHome() {

        signUpFlagLiveData.postValue(ValueWrapper(true))
    }

    fun validateUserSignUpData(email: String, password: String, confirmPassword: String) {

        // Validate user signUp data
        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {

            _fillAllFieldsLiveData.postValue(ValueWrapper(true))
        }
        else if (!isEmailValid(email)) {

            _inValidEmailAddressLiveData.postValue(ValueWrapper(true))

        } else if (password != confirmPassword) {

            _passwordMismatchLiveData.postValue(ValueWrapper(true))

        } else {

            addUserLiveData.postValue(UserPojo(email = email, password = password))
        }


    }

    private fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }


    fun signUp(userPojo: UserPojo) {

        viewModelScope.launch {
//
//            Log.i("TAG", "signUp: returned result ${userRepo?.signUp(userPojo)}")

            val result = userRepo?.signUp(userPojo)
            Log.i("TAG", "signUp: returned result ${result}")

            if ( result == -1L) {
                _userAlreadyExistLiveData.postValue(ValueWrapper(true))
            } else {
                _homeUserLiveDate.postValue(userPojo)
            }

//            when(userRepo?.signUp(userPojo)) {
//
//                -1L -> _userNotFoundLiveData.postValue(true)
//                else -> _homeUserLiveDate.postValue(userPojo)
//            }
        }

    }

    class SingUpViewModelFactory(private val userRepo: UserRepoInterface?) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SignUpViewModel(userRepo) as T
        }
    }
}


