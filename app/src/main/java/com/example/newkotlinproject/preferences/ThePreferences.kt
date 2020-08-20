package com.example.newkotlinproject.preferences

import android.content.SharedPreferences
import android.util.Log
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ThePreferences : KoinComponent{

    companion object{
        val instance = ThePreferences()
    }

    private val sharedPreferences : SharedPreferences by inject()
    private val sharedEditor = sharedPreferences.edit()

    fun registerLoggedID(id: Long) {
        sharedEditor.putLong("loggedID", id)
        sharedEditor.commit()
    }

    fun checkLoggedId() : Boolean{
        Log.d("LOGEDDDDD", sharedPreferences.contains("loggedID").toString())
        return sharedPreferences.contains("loggedID")
    }

    fun prefLogOff() {
        sharedEditor.remove("loggedID")
        sharedEditor.commit()
    }

}