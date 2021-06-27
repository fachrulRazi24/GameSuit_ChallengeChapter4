package com.example.binaracademy.gamesuit_challengechapter4.ui.loadingpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.binaracademy.gamesuit_challengechapter4.R
import com.example.binaracademy.gamesuit_challengechapter4.ui.landingpage.LandingPageActivity
import com.example.binaracademy.gamesuit_challengechapter4.ui.loadingpage.dialog.LoadingDialog

class LoadingPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R .layout.activity_loading_page)
        val loading = LoadingDialog(this)
        supportActionBar?.hide()
        loading.startLoading()
        val intentLandingPages = Intent(this, LandingPageActivity::class.java)
        val handler = Handler()
        handler.postDelayed(object : Runnable{
            override fun run() {
                loading.isDismiss()
                startActivity(intentLandingPages)
            }
        }, 5000)
    }
}
