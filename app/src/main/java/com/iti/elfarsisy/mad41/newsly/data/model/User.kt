package com.iti.elfarsisy.mad41.newsly.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey
    private var email: String,
    private var password: String

        )
