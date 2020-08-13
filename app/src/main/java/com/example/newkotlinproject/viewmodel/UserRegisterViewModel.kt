package com.example.newkotlinproject.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newkotlinproject.model.User
import com.example.newkotlinproject.repository.DataRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class UserRegisterViewModel : ViewModel() {

    private var createdUserMutable = MutableLiveData<User>()
    val createdUser : LiveData<User> = createdUserMutable

    private var exception = MutableLiveData<String>()
    val theException: LiveData<String> = exception

    fun createUser(email: String, username: String, password: String, image: Bitmap){

        try{

            //Inicio de uma Coroutina
            viewModelScope.launch {
                createdUserMutable.value = DataRepository.instance.createUser(email, username, password, image)
            }

        }catch (e: Exception){
            exception.value = e.message
        }

    }

}