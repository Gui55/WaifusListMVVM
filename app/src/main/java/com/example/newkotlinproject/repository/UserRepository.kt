package com.example.newkotlinproject.repository

import android.graphics.Bitmap
import com.example.newkotlinproject.database.UserDAO
import com.example.newkotlinproject.model.User
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class UserRepository : KoinComponent{

    companion object{
        val instance = UserRepository()
    }

    private val userDao : UserDAO by inject()

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

}