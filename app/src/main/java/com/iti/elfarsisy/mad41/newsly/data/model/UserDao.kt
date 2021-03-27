package com.iti.elfarsisy.mad41.newsly.data.model

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface UserDao {

    @Insert
    suspend fun signUp(user: User)


}