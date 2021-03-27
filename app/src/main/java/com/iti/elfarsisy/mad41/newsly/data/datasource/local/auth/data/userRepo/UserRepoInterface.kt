package com.iti.elfarsisy.mad41.newsly.data.datasource.local.auth.data.userRepo

import androidx.lifecycle.LiveData
import com.iti.elfarsisy.mad41.newsly.data.model.UserPojo

interface UserRepoInterface {

    suspend fun signUp(user: UserPojo):Long?
    suspend fun login(user: UserPojo):UserPojo?
    fun getAllUsers(): LiveData<List<UserPojo>>?
}