package com.example.newkotlinproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newkotlinproject.repository.DataRepository

class WaifuDetailsViewModel : ViewModel() {

    private var mutableInformation = MutableLiveData<ArrayList<String>>()
    val information: LiveData<ArrayList<String>> = mutableInformation

    fun fetchInformation(){

        var theWaifu = DataRepository.instance.getClickedWaifu()
        var waifuInformation = ArrayList<String>()

        waifuInformation.add(0, theWaifu.name)
        waifuInformation.add(1, theWaifu.photo)
        waifuInformation.add(2, theWaifu.description)

        mutableInformation.value = waifuInformation

    }

}