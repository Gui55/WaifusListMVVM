package com.example.newkotlinproject.data.model

data class Waifu(val id: Int, val name: String, val source: String, val description: String,
                 val photo: String, var likes: Int, var dislikes: Int)