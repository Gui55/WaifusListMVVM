package com.example.newkotlinproject.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(var email: String, var username: String, var password: String, var image: Bitmap){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}