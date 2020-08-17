package com.example.newkotlinproject.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newkotlinproject.repository.DataRepository
import kotlinx.coroutines.runBlocking

class WaifuDetailsViewModel : ViewModel() {

    private var mutableInformation = MutableLiveData<ArrayList<String>>()
    val information: LiveData<ArrayList<String>> = mutableInformation

    fun fetchInformation(){

        var theWaifu = DataRepository.instance.getClickedWaifu()
        var waifuInformation = ArrayList<String>()

        waifuInformation.add(0, theWaifu.name)
        waifuInformation.add(1, theWaifu.photo)
        waifuInformation.add(2, theWaifu.description)
        waifuInformation.add(3, theWaifu.source)

        mutableInformation.value = waifuInformation

    }

    fun getPhotoByUsername(author: String): Bitmap {

        lateinit var bmp : Bitmap

        runBlocking {
            bmp = DataRepository.instance.getUserImageByName(author)
        }

        return bmp
    }

}