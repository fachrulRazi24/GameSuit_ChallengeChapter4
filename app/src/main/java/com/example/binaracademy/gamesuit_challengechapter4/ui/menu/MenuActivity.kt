package com.example.binaracademy.gamesuit_challengechapter4.ui.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.binaracademy.gamesuit_challengechapter4.R
import com.example.binaracademy.gamesuit_challengechapter4.databinding.ActivityMenuBinding
import com.example.binaracademy.gamesuit_challengechapter4.ui.game.ComputerActivity
import com.example.binaracademy.gamesuit_challengechapter4.ui.game.PlayerActivity
import com.google.android.material.snackbar.Snackbar

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    var playMode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val playername = intent.getStringExtra("playername")

        binding.tvMenuPvp.text = "$playername vs PLAYER 2"
        binding.tvMenuPve.text = "$playername vs CPU"
        Snackbar.make(binding.root, "Welcome $playername", Snackbar.LENGTH_LONG).show()

        binding.llMenuPvp.setOnClickListener {
            binding.llMenuPvp.setBackgroundResource(R.drawable.bg_action_button_red)
            binding.llMenuPvp.isClickable = true
            binding.llMenuPve.isClickable = false
            playMode = 1
        }
        binding.llMenuPve.setOnClickListener {
            binding.llMenuPve.setBackgroundResource(R.drawable.bg_action_button_red)
            binding.llMenuPvp.isClickable = false
            binding.llMenuPve.isClickable = true
            playMode = 2
        }
        binding.btnMenuLetsPlay.setOnClickListener {
            navigateToGame("$playername")
        }
    }

    fun navigateToGame(username: String) {
        if (playMode == 2) {
            val intent = Intent(this, ComputerActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        } else if (playMode == 1) {
            val intent = Intent(this, PlayerActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        } else {
            Snackbar.make(binding.root, getString(R.string.text_please_choose_playing_mode), Snackbar.LENGTH_SHORT).show()
        }
    }
}