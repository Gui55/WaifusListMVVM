package com.example.newkotlinproject.model

import com.google.gson.annotations.SerializedName

data class Comment(var author: String, @SerializedName("waifu") var character: String, var content: String, var likes : Int, var dislikes : Int)