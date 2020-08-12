package com.example.newkotlinproject.koin

import androidx.room.Room
import com.example.newkotlinproject.database.UserDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val theModule = module {
    single { Room.databaseBuilder(androidContext(), UserDatabase::class.java, "userdatabase.db").build().userDao() }
}