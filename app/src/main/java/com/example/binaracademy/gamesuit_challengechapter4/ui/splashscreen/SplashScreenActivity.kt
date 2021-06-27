package com.example.binaracademy.gamesuit_challengechapter4.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.binaracademy.gamesuit_challengechapter4.R
import com.example.binaracademy.gamesuit_challengechapter4.ui.loadingpage.LoadingPageActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        val logoSS = findViewById<ImageView>(R.id.iv_splash_screen_title)
        Glide.with(this).
        load(R.drawable.ic_title_game)
            .into(logoSS)

        val intentLoadingPages = Intent(this, LoadingPageActivity::class.java)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(
            {
                this.finish()
                startActivity(intentLoadingPages)
            }, 4000
        )
    }
}