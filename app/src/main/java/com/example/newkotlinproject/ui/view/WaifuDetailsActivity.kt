package com.example.newkotlinproject.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newkotlinproject.R
import com.example.newkotlinproject.viewmodel.WaifuDetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_waifu_details.*

class WaifuDetailsActivity : AppCompatActivity() {

    private var viewModel = WaifuDetailsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waifu_details)

        viewModel = ViewModelProvider(this).get(WaifuDetailsViewModel::class.java)
        observations()

        viewModel.fetchInformation()
    }

    private fun observations() {

        viewModel.information.observe(this, Observer {
            waifuDetailsTitle.text = it[0]
            waifuDetailsDescription.text = it[2]
            waifuDetailsSubtitle.text = it[3]

            Picasso.get().load(it[1]).into(waifuDetailsImage)
        })
    }

    fun goToCreateComment(view: View) {
        startActivity(Intent(this, CreateCommentActivity::class.java))
    }
}