package com.example.newkotlinproject.database

import android.graphics.Bitmap
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newkotlinproject.model.User

@Dao
interface UserDAO {

    @Insert
    suspend fun addUser(user: User) : Long

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Long) : User

    @Query("SELECT * FROM user WHERE username = :username")
    suspend fun getUserByName(username: String) : User

    @Query("SELECT image FROM user WHERE username = :username")
    suspend fun getUserImageByName(username: String) : Bitmap

}