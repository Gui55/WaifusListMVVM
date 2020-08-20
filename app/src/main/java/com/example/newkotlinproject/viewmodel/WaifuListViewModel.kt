package com.example.newkotlinproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newkotlinproject.model.Waifu
import com.example.newkotlinproject.repository.DataRepository

class WaifuListViewModel : ViewModel() {

    private var mutableWaifus = DataRepository.instance.getTheWaifus()
    val theWaifus : LiveData<ArrayList<Waifu>> = mutableWaifus

    private var mutableChangeActivity = MutableLiveData<Boolean>()
    val changeActivity : LiveData<Boolean> = mutableChangeActivity

    fun passToRepClickWaifu(waifu: Waifu) {
        DataRepository.instance.setClickedWaifu(waifu)
        mutableChangeActivity.value = true
    }

    fun deactivateChange(){
        mutableChangeActivity.value = false
    }

    fun preferencesLogOff() {
        DataRepository.instance.preferencesLogOff()
    }

}