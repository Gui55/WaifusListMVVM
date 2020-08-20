package com.example.newkotlinproject.data.webservices

import com.example.newkotlinproject.data.model.Comment
import com.example.newkotlinproject.data.model.Waifu
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface Requisition {

    @GET("Waifu")
    fun getWaifus() : Call<ArrayList<Waifu>>

    @PATCH("Waifu/{id}")
    fun updateComment(@Path("id") id: Int, @Body comment: Comment) : Call<ResponseBody>

}