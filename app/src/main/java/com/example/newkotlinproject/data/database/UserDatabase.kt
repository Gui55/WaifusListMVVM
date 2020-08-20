package com.example.newkotlinproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newkotlinproject.data.model.User
import com.example.newkotlinproject.util.TheBitmapConverters

@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(TheBitmapConverters::class)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao() : UserDAO

}