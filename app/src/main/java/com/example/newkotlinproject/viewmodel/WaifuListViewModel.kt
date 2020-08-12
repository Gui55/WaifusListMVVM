package com.example.newkotlinproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newkotlinproject.model.Waifu
import com.example.newkotlinproject.repository.UserRepository

class WaifuListViewModel : ViewModel() {

    private var mutableWaifus = UserRepository.instance.getTheWaifus()
    val theWaifus : LiveData<ArrayList<Waifu>> = mutableWaifus

}