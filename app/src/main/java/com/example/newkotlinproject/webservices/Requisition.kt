package com.example.newkotlinproject.webservices

import com.example.newkotlinproject.model.Waifu
import retrofit2.Call
import retrofit2.http.GET

interface Requisition {

    @GET("Waifu")
    fun getWaifus() : Call<ArrayList<Waifu>>

}