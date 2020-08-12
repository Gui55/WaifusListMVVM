package com.example.newkotlinproject.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newkotlinproject.R
import com.example.newkotlinproject.ui.adapter.WaifuAdapter
import com.example.newkotlinproject.viewmodel.WaifuListViewModel
import kotlinx.android.synthetic.main.activity_waifus_list.*

class WaifusListActivity : AppCompatActivity() {

    private var viewModel = WaifuListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waifus_list)

        viewModel = ViewModelProvider(this).get(WaifuListViewModel::class.java)

        configureRecyclerView()
        observations()
    }

    private fun configureRecyclerView() {
        waifuRecycler.layoutManager = LinearLayoutManager(this)
    }

    private fun observations() {
        viewModel.theWaifus.observe(this, Observer {
            waifuRecycler.adapter = WaifuAdapter(it)
        })
    }
}