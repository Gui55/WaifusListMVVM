package com.example.newkotlinproject.koin

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.newkotlinproject.BuildConfig
import com.example.newkotlinproject.database.UserDatabase
import com.example.newkotlinproject.webservices.Requisition
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val theModule = module {
    single { Room.databaseBuilder(androidContext(), UserDatabase::class.java, "userdatabase.db").build().userDao() }
    single {Retrofit.Builder()
        .baseUrl(BuildConfig.ApiUrl
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Requisition::class.java)}

    single<SharedPreferences>{
        androidContext().getSharedPreferences("Logged_User", Context.MODE_PRIVATE)
    }
}
