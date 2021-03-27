package com.iti.elfarsisy.mad41.newsly.features.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import com.iti.elfarsisy.mad41.newsly.data.datasource.local.auth.data.userRepo.UserRepoInterface
import com.iti.elfarsisy.mad41.newsly.data.model.UserPojo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel(private val repo: UserRepoInterface) : ViewModel() {

    private var _userNotFoundLiveData = MutableLiveData<Boolean>()
    val userNotFoundLiveData = _userNotFoundLiveData

    private var _userListLiveData = MediatorLiveData<List<UserPojo>>()
    val userListLiveData = _userListLiveData

    private var _userLiveData = MutableLiveData<UserPojo>()
    val userLiveData = _userLiveData

    fun signIn(user: UserPojo) = viewModelScope.launch {
        val user: UserPojo? = repo.login(user = user)

        if (user != null) {
            _userLiveData.postValue(user!!)
        } else {
            _userNotFoundLiveData.postValue(true)
        }
    }

}


class SigninViewModelFactory(private val repository: UserRepoInterface) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SignInViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}