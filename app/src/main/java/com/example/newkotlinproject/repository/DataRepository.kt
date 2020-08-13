package com.example.newkotlinproject.repository

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import com.example.newkotlinproject.database.UserDAO
import com.example.newkotlinproject.model.User
import com.example.newkotlinproject.model.Waifu
import com.example.newkotlinproject.webservices.Requisition
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataRepository : KoinComponent{

    companion object{
        val instance = DataRepository()
    }

    private val userDao : UserDAO by inject()
    private val requisition: Requisition by inject()
    lateinit private var clickedWaifu : Waifu

    //Suspend functions so podem ser chamadas de outra suspend function, ou de uma coroutina
    suspend fun createUser(email: String, username: String, password: String, image: Bitmap) : User{
        var newId = userDao.addUser(
            User(
                email, username, password, image
            )
        )

        return userDao.getUserById(newId)
    }

    suspend fun userIsCorrect(name: String, password: String): Int{

        var user = userDao.getUserByName(name)

        when {
            user==null -> {
                return 2
            }
            user.password==password -> {
                return 0
            }
            user.password!=password -> {
                return 1
            }
        }

        return 10

    }

    suspend fun getUserImageByName(name: String) : Bitmap {
        return userDao.getUserImageByName(name)
    }

    fun getTheWaifus() : MutableLiveData<ArrayList<Waifu>>{

        var theWaifus = MutableLiveData<ArrayList<Waifu>>()

        requisition.getWaifus().enqueue(object: Callback<ArrayList<Waifu>>{
            override fun onFailure(call: Call<ArrayList<Waifu>>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<ArrayList<Waifu>>,
                response: Response<ArrayList<Waifu>>
            ) {
                theWaifus.value = response.body()
            }

        })

        return theWaifus

    }

    fun setClickedWaifu(waifu: Waifu) {
        clickedWaifu = waifu
    }

    fun getClickedWaifu() : Waifu{
        return clickedWaifu
    }

}