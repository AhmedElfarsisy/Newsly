package com.iti.elfarsisy.mad41.newsly.data.datasource.local.auth.data.userRepo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iti.elfarsisy.mad41.newsly.data.datasource.local.auth.data.db.NewsDB
import com.iti.elfarsisy.mad41.newsly.data.model.UserPojo

class UserRepo private constructor(): UserRepoInterface {

    private var userListLiveData = MutableLiveData<List<UserPojo>>()



    companion object {

        @Volatile
        private var instance: UserRepo? = null
        private var newsDB: NewsDB? = null

        fun getUserRepoInstance(context: Context): UserRepoInterface? {

            if (instance == null) {

                synchronized(UserRepo) {
                    if (instance == null) {
                        newsDB = NewsDB.getInstance(context)
                        instance = UserRepo()
                    }
                }
            }



            return instance

        }
    }

    override suspend fun signUp(userPojo: UserPojo):Long? {

        Log.i("TAG", "signUp: hello from repo")
        return newsDB?.userDao()?.signUp(userPojo)

    }

    override suspend fun login(user: UserPojo): UserPojo? {
        Log.i("TAG", "signIn: hello from repo")
        return newsDB?.userDao()?.getUser(user.email, user.password)
    }

    override fun getAllUsers(): LiveData<List<UserPojo>>? {
       return newsDB?.userDao()?.getAllUsers()

    }
}