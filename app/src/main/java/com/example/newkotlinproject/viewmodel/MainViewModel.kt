package com.example.newkotlinproject.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newkotlinproject.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val mutableMsg = MutableLiveData<String>()
    val msg : LiveData<String> = mutableMsg

    private val mutableSelImage = MutableLiveData<Bitmap>()
    val selImage : LiveData<Bitmap> = mutableSelImage

    fun login(name: String, password: String){

        if(isItFullfilled(name, password)){
            viewModelScope.launch {
                when(UserRepository.instance.userIsCorrect(name, password)){

                    0 -> mutableMsg.value = "Login successful"
                    1 -> mutableMsg.value = "Wrong password"
                    2 -> mutableMsg.value = "User does not exist"
                    10 -> mutableMsg.value = "Error"

                }
            }
        } else {
            mutableMsg.value = "There are empty fields that needs to be fullfilled"
        }
    }

    fun getUserImageByName(name: String) {

        viewModelScope.launch {
            mutableSelImage.value = UserRepository.instance.getUserImageByName(name)
        }
    }

    fun isItFullfilled(name: String, password: String) : Boolean{
        return name.isNotEmpty() && password.isNotEmpty()
    }

}