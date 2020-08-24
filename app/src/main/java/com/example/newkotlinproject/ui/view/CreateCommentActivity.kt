package com.example.newkotlinproject.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newkotlinproject.R
import com.example.newkotlinproject.viewmodel.CreateCommentViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_create_comment.*

class CreateCommentActivity : AppCompatActivity() {

    private var viewModel = CreateCommentViewModel()
    var buttonPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_comment)

        viewModel = ViewModelProvider(this).get(CreateCommentViewModel::class.java)
        observations()
    }

    private fun observations() {
        viewModel.fetchCurrentUser()
        viewModel.currentUser.observe(this, Observer {
            createCommentUserName.text = it.username
            createCommentUserPhoto.setImageBitmap(it.image)
        })

        viewModel.insReponse.observe(this, Observer {

            if(buttonPressed){
                var text : String = ""

                when(it){
                    true ->{
                        text = "Comment succesfully updated!"
                        finish()
                    }

                    false ->{
                        text = "The comment could not be updated"
                    }
                }

                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun sendTheComment(view: View) {
        buttonPressed = true
        viewModel.registerComment(createCommentUserName.text.toString(), createCommentContent.text.toString())
    }
}