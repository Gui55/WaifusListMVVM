package com.example.newkotlinproject.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.newkotlinproject.R
import com.example.newkotlinproject.viewmodel.CreateCommentViewModel
import kotlinx.android.synthetic.main.activity_create_comment.*

class CreateCommentActivity : AppCompatActivity() {

    private var viewModel = CreateCommentViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_comment)

        viewModel = ViewModelProvider(this).get(CreateCommentViewModel::class.java)
    }

    fun sendTheComment(view: View) {
        viewModel.registerComment(createCommentUserName.text.toString(), createCommentContent.text.toString())
    }
}