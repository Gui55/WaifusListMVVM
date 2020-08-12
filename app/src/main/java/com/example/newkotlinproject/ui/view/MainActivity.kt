package com.example.newkotlinproject.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newkotlinproject.R
import com.example.newkotlinproject.koin.theModule
import com.example.newkotlinproject.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.startKoin

class MainActivity : AppCompatActivity() {

    private var viewmodel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)

        startKoin(this, listOf(theModule))

        observations()
        imageChangeListener()
    }

    private fun imageChangeListener() {
        loginUsername.setOnFocusChangeListener { _: View, _: Boolean ->
            val txt = loginUsername.text.toString()
            if(txt!=""){
                viewmodel.getUserImageByName(txt)
            }
        }
    }

    private fun observations() {
        viewmodel.msg.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

            if(it=="Login successful"){
                startActivity(Intent(this, WaifusListActivity::class.java))
                finish()
            }
        })

        viewmodel.selImage.observe(this, Observer {
            if(it!=null){
                loginPhoto.setImageBitmap(it)
            } else {
                loginPhoto.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.waifus_symbol_a))
            }

        })
    }

    fun goToRegister(view: View) {
        startActivity(Intent(this, UserRegisterActivity::class.java))
    }

    fun loginFunction(view: View) {
        viewmodel.login(loginUsername.text.toString(), loginPassword.text.toString())
    }
}