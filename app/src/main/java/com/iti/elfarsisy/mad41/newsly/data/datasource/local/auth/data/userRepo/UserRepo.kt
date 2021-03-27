package com.iti.elfarsisy.mad41.newsly.data.datasource.local.auth.data.userRepo

import android.content.Context
import com.iti.elfarsisy.mad41.newsly.data.datasource.local.auth.data.db.NewsDB
import com.iti.elfarsisy.mad41.newsly.data.model.User

class UserRepo private constructor(): UserRepoInterface {

    companion object {

        @Volatile
        private var instance: UserRepo? = null
        private var newsDB: NewsDB? = null

        fun getUserRepoInstance(context: Context): UserRepoInterface? {

            if (instance == null) {

                synchronized(UserRepo) {
                    if (instance == null) {
                        newsDB = NewsDB.getInstance(context)
                    }
                }
            }



            return instance

        }
    }

    override suspend fun signUp(user: User) {

        newsDB?.userDao()?.signUp(user)

    }
}