package com.iti.elfarsisy.mad41.newsly.data.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun signUp(userPojo: UserPojo): Long

    @Query("Select * from user_table Where email = :email And password = :password")
    suspend fun getUser(email: String, password: String): UserPojo?

    @Query("Select * from user_table")
    fun getAllUsers(): LiveData<List<UserPojo>>?


}