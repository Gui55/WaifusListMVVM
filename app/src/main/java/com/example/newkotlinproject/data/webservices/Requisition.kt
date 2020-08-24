package com.example.newkotlinproject.data.webservices

import com.example.newkotlinproject.data.model.Comment
import com.example.newkotlinproject.data.model.Waifu
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Requisition {

    @GET("Waifu")
    fun getWaifus() : Call<ArrayList<Waifu>>

    @POST("Comment")
    fun insertComment(@Body comment: Comment) : Call<Comment>


}