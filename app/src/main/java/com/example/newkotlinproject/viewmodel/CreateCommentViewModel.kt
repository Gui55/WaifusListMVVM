package com.example.newkotlinproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.newkotlinproject.data.repository.DataRepository

class CreateCommentViewModel : ViewModel() {
    fun registerComment(name: String, content: String) {
        var theWaifuId = DataRepository.instance.getClickedWaifu().id
        DataRepository.instance.registerComment(name, content, theWaifuId)
    }
}