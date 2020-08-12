package com.example.newkotlinproject.ui.view

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newkotlinproject.R
import com.example.newkotlinproject.viewmodel.UserRegisterViewModel
import kotlinx.android.synthetic.main.activity_user_register.*
import java.util.jar.Manifest

class UserRegisterActivity : AppCompatActivity() {

    private var viewModel = UserRegisterViewModel()

    private val requestTakePhoto = 1
    private val requestGetImage = 2
    private val thePermissionCamera = 3
    private val thePermissionStorage = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)

        viewModel = ViewModelProvider(this).get(UserRegisterViewModel::class.java)
        observations()
    }

    private fun observations() {
        viewModel.createdUser.observe(this, Observer {
            Toast.makeText(this, "User ${it.username} sucessfully created", Toast.LENGTH_SHORT).show()
            finish()
        })

        viewModel.theException.observe(this, Observer {
            Toast.makeText(this, "Error: $it", Toast.LENGTH_SHORT).show()
        })
    }

    fun createTheUser(view: View) {
        viewModel.createUser(

            registerEmail.text.toString(),
            registerUsername.text.toString(),
            registerPassword.text.toString(),
            registerUserPhoto.drawable.toBitmap()

        )
    }

    fun photoFromCamera(view: View) {

        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED){
            startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), requestTakePhoto)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), thePermissionCamera)
        }
    }

    fun photoFromStorage(view: View){

        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            var intent = Intent(Intent.ACTION_PICK)
            intent.type = ("image/*")
            startActivityForResult(intent, requestGetImage)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), thePermissionStorage)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK){

            when(requestCode){

                requestTakePhoto -> if (data != null) {
                    registerUserPhoto.setImageBitmap(data.extras?.get("data") as Bitmap?)
                }

                requestGetImage -> if (data != null) {
                    registerUserPhoto.setImageURI(data.data)
                }

            }

        }

    }

    override fun onRequestPermissionsResult(
        requestCode: kotlin.Int,
        permissions: kotlin.Array<out kotlin.String>,
        grantResults: kotlin.IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){

            thePermissionCamera ->

                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT).show()

                } else {

                    Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT).show()

                }

            thePermissionStorage ->

                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    Toast.makeText(this, "Storage permission granted", Toast.LENGTH_SHORT).show()

                } else {

                    Toast.makeText(this, "Storage permission denied", Toast.LENGTH_SHORT).show()

                }

        }
    }
}