package com.example.divyapractical.database

import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<User>)

    @Update
    fun updateUsers(users: List<User>)

    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>

    @Query("SELECT id FROM user Where name = :nameToCheck")
    fun getId(nameToCheck: String): Long
}