package com.iti.elfarsisy.mad41.newsly.data.datasource.local.auth.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iti.elfarsisy.mad41.newsly.data.model.User
import com.iti.elfarsisy.mad41.newsly.data.model.UserDao

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class NewsDB : RoomDatabase() {

    abstract  fun userDao(): UserDao?

    companion object {
        private var instance: NewsDB? = null
        fun getInstance(context: Context): NewsDB? {
            if (instance == null) {
                synchronized(NewsDB::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context,
                            NewsDB::class.java, "news_database"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return instance
        }
    }
}

