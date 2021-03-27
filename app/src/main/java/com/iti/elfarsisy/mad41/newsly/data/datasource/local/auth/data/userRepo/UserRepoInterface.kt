package com.iti.elfarsisy.mad41.newsly.data.datasource.local.auth.data.userRepo

import com.iti.elfarsisy.mad41.newsly.data.model.User

interface UserRepoInterface {

    suspend fun signUp(user: User)
}