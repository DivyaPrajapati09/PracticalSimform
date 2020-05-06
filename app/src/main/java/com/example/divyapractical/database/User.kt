package com.example.divyapractical.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var name: String,
    var gender: String,
    var email: String,
    var dateOfBirth: String,
    var image: String
)