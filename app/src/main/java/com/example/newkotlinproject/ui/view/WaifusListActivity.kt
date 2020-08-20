package com.example.newkotlinproject.ui.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newkotlinproject.R
import com.example.newkotlinproject.model.Waifu
import com.example.newkotlinproject.ui.adapter.WaifuAdapter
import com.example.newkotlinproject.viewmodel.WaifuListViewModel
import kotlinx.android.synthetic.main.activity_waifus_list.*

class WaifusListActivity : AppCompatActivity() {

    private var viewModel = WaifuListViewModel()
    lateinit var sharedPref: SharedPreferences
    lateinit var sharedEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waifus_list)

        viewModel = ViewModelProvider(this).get(WaifuListViewModel::class.java)

        sharedPref = getSharedPreferences("Logged_User", 0)
        sharedEditor = sharedPref.edit()

        configureRecyclerView()
        observations()
    }

    private fun configureRecyclerView() {
        waifuRecycler.layoutManager = LinearLayoutManager(this)
    }

    private fun observations() {
        viewModel.theWaifus.observe(this, Observer {
            waifuRecycler.adapter = WaifuAdapter(it, viewModel)
        })

        viewModel.changeActivity.observe(this, Observer {
            if(it){
                viewModel.deactivateChange()
                startActivity(Intent(this, WaifuDetailsActivity::class.java))
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.the_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        when(item.itemId){

            R.id.logOffItem ->{

                sharedEditor.remove("loggedId")
                sharedEditor.commit()
                finish()

            }
        }

        return true
    }

}