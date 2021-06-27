package com.example.binaracademy.gamesuit_challengechapter4.ui.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.binaracademy.gamesuit_challengechapter4.R
import com.example.binaracademy.gamesuit_challengechapter4.databinding.ActivityPlayerBinding
import com.example.binaracademy.gamesuit_challengechapter4.databinding.FragmentResultDialogBinding
import com.example.binaracademy.gamesuit_challengechapter4.ui.menu.MenuActivity

class PlayerActivity : AppCompatActivity() {

    private val TAG = PlayerActivity::class.java.simpleName
    private lateinit var binding: ActivityPlayerBinding
    private var player: Int = -1
    private var player2: Int = -1
    private var isGameFinished: Boolean = false
    private var player2pick: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        introToast()
        setClickEvent()
    }

    private fun setClickEvent() {
        val username = intent.getStringExtra("username")
        binding.tvPlayerName.text = username
        binding.ivIcPlayerRock.setOnClickListener {
            if (!isGameFinished) {
                Log.d(TAG, "Player Memilih Batu")
                binding.ivIcPlayerRock.setBackgroundResource(0)
                binding.ivIcPlayerPaper.isClickable = false
                binding.ivIcPlayerScissor.isClickable = false
                binding.ivIcComputerPaper.isClickable = false
                binding.ivIcComputerRock.isClickable = false
                binding.ivIcComputerScissor.isClickable = false
                player = 0
                Toast.makeText(this, getString(R.string.text_player2_turn), Toast.LENGTH_SHORT).show()
                player2pick = true
                player2Turn()
            }
        }
        binding.ivIcPlayerPaper.setOnClickListener {
            if (!isGameFinished) {
                Log.d(TAG, "Player Memilih Kertas")
                binding.ivIcPlayerPaper.setBackgroundResource(0)
                binding.ivIcPlayerRock.isClickable = false
                binding.ivIcPlayerScissor.isClickable = false
                binding.ivIcComputerPaper.isClickable = false
                binding.ivIcComputerRock.isClickable = false
                binding.ivIcComputerScissor.isClickable = false
                player = 1
                Toast.makeText(this, getString(R.string.text_player2_turn), Toast.LENGTH_SHORT).show()
                player2pick = true
                player2Turn()
            }
        }
        binding.ivIcPlayerScissor.setOnClickListener {
            if (!isGameFinished) {
                Log.d(TAG, "Player Memilih Gunting")
                binding.ivIcPlayerRock.isClickable = false
                binding.ivIcPlayerPaper.isClickable = false
                binding.ivIcComputerPaper.isClickable = false
                binding.ivIcComputerRock.isClickable = false
                binding.ivIcComputerScissor.isClickable = false
                binding.ivIcPlayerScissor.setBackgroundResource(0)
                player = 2
                Toast.makeText(this, getString(R.string.text_player2_turn), Toast.LENGTH_SHORT).show()
                player2pick = true
                player2Turn()
            }
        }
        binding.ivIcRefresh.setOnClickListener {
            Log.d(TAG, "Reset Game, Enjoy !")
            if (isGameFinished) {
                isGameFinished = false
                resetGame()
            }
        }
        binding.ivIcExit.setOnClickListener {
            Log.d(TAG, "Exit To Main Menu!")
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        }
        binding.tvStartBattle.setOnClickListener {
            if (!player2pick == false) {
                player2Turn()
                startGame()
            } else {
                resetGame()
            }
        }
    }


    fun player2Turn() {
        binding.ivIcComputerPaper.setOnClickListener {
            if (player2pick == true) {
                binding.ivIcComputerPaper.setBackgroundResource(0)
                binding.ivIcComputerRock.isClickable = false
                binding.ivIcComputerScissor.isClickable = false
                binding.ivIcPlayerRock.isClickable = false
                binding.ivIcPlayerPaper.isClickable = false
                binding.ivIcPlayerScissor.isClickable = false
                player2 = 1
                Toast.makeText(this, getString(R.string.text_click_start_battle), Toast.LENGTH_SHORT).show()
            }
        }
        binding.ivIcComputerRock.setOnClickListener {
            if (player2pick == true) {
                binding.ivIcComputerPaper.isClickable = false
                binding.ivIcComputerRock.setBackgroundResource(0)
                binding.ivIcComputerScissor.isClickable = false
                binding.ivIcPlayerRock.isClickable = false
                binding.ivIcPlayerPaper.isClickable = false
                binding.ivIcPlayerScissor.isClickable = false
                player2 = 0
                Toast.makeText(this, getString(R.string.text_click_start_battle), Toast.LENGTH_SHORT).show()
            }
        }
        binding.ivIcComputerScissor.setOnClickListener {
            if (player2pick == true) {
                binding.ivIcComputerPaper.isClickable = false
                binding.ivIcComputerRock.isClickable = false
                binding.ivIcComputerScissor.setBackgroundResource(0)
                binding.ivIcPlayerRock.isClickable = false
                binding.ivIcPlayerPaper.isClickable = false
                binding.ivIcPlayerScissor.isClickable = false
                player2 = 2
                Toast.makeText(this, getString(R.string.text_click_start_battle), Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun startGame() {
        if ((player.plus(1)).mod(3) == player2) {
            val result = getString(R.string.text_lose)
            val username = "Player 2"
            val player2choose = ""
            val playerchoose = ""
            Log.d(TAG, "Player 2 WIN")
            resultDialog(result, username, player2choose, playerchoose)
        } else if (player == player2) {
            val result = "DRAW !"
            val username = " "
            val player2choose = ""
            val playerchoose = ""
            Log.d(TAG, "Hasil DRAW")
            resultDialog(result, username, player2choose, playerchoose)
        } else {
            val result = "WIN !"
            val username = "PLAYER"
            val player2choose = ""
            val playerchoose = ""
            Log.d(TAG, "Player Menang")
            resultDialog(result, username, player2choose, playerchoose)
        }
        isGameFinished = true
        binding.ivIcRefresh.setImageResource(R.drawable.ic_refresh_red)
    }


    private fun resetGame() {
        isGameFinished = false
        player = -1
        player2 = -1
        binding.ivIcRefresh.setImageResource(0)
        binding.ivIcPlayerScissor.isClickable = true
        binding.ivIcPlayerPaper.isClickable = true
        binding.ivIcPlayerRock.isClickable = true
        refreshGameState()
        introToast()
    }

    private fun refreshGameState() {
        binding.ivIcComputerScissor.setBackgroundResource(0)
        binding.ivIcComputerRock.setBackgroundResource(0)
        binding.ivIcComputerPaper.setBackgroundResource(0)
        binding.ivIcPlayerScissor.setBackgroundResource(0)
        binding.ivIcPlayerRock.setBackgroundResource(0)
        binding.ivIcPlayerPaper.setBackgroundResource(0)
    }

    fun resultDialog(result: String, username: String, player2choose: String, playerchoose: String) {
        val username = intent.getStringExtra("username")
        val builder = AlertDialog.Builder(this)
        val binding = FragmentResultDialogBinding.inflate(layoutInflater)
        builder.setView(binding.root)
        val dialog = builder.create()
        binding.tvPlayer.text = username
        binding.tvGameResult.text = result
        binding.tvComputerChoose.text = player2choose
        binding.tvPlayerChoose.text = playerchoose
        binding.btnPlayAgain.setOnClickListener {
            resetGame()
            dialog.dismiss()
        }
        binding.btnBackToMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        }
        dialog.setCancelable(false)
        dialog.show()
    }

    fun introToast() {
        Toast.makeText(this, getString(R.string.text_player1_pick_first), Toast.LENGTH_SHORT).show()
    }
}