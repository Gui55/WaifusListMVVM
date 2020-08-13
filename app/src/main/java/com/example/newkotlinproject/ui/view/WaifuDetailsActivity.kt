package com.example.newkotlinproject.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

            Picasso.get().load(it[1]).into(waifuDetailsImage)
        })
    }
}