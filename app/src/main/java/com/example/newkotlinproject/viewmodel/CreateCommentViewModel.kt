package com.example.newkotlinproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newkotlinproject.data.model.User
import com.example.newkotlinproject.data.repository.DataRepository
import kotlinx.coroutines.launch

class CreateCommentViewModel : ViewModel() {

    private var currentUserMutable = MutableLiveData<User>()
    var currentUser : LiveData<User> = currentUserMutable

    private var mutableInsResponse = DataRepository.instance.answer
    var insReponse : LiveData<Boolean> = mutableInsResponse

    fun registerComment(name: String, content: String) {
        DataRepository.instance.registerComment(name, content)
    }

    fun fetchCurrentUser(){
        viewModelScope.launch {

            currentUserMutable.value = DataRepository.instance.getUserByID()

        }
    }
}