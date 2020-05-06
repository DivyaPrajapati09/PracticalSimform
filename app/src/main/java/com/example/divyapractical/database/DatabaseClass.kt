package com.example.divyapractical.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], exportSchema = false, version = 1)
abstract class DatabaseClass : RoomDatabase() {

    companion object {

        val DB_NAME: String = "user_db"
        var databaseClassInstance: DatabaseClass? = null

        public fun getDatabaseInstance(context: Context): DatabaseClass {
            if (databaseClassInstance == null) {
                databaseClassInstance =
                    Room.databaseBuilder(context, DatabaseClass::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration().build()
            }
            return databaseClassInstance as DatabaseClass
        }
    }

    public abstract fun UserDao(): UserDao
}