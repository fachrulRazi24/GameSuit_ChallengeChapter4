package com.example.binaracademy.gamesuit_challengechapter4.ui.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.binaracademy.gamesuit_challengechapter4.R
import com.example.binaracademy.gamesuit_challengechapter4.databinding.ActivityComputerBinding
import com.example.binaracademy.gamesuit_challengechapter4.databinding.FragmentResultDialogBinding
import com.example.binaracademy.gamesuit_challengechapter4.enum.GameCharacter
import com.example.binaracademy.gamesuit_challengechapter4.ui.menu.MenuActivity
import kotlin.random.Random

class ComputerActivity : AppCompatActivity() {

    private val TAG = ComputerActivity::class.java.simpleName
    private lateinit var binding: ActivityComputerBinding
    private var player: Int = -1
    private var computer: Int = -1
    private var isGameFinished: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComputerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setClickEvent()
    }


    private fun setClickEvent() {
        var playerSelect = ""
        var computerSelect = ""
        val username = intent.getStringExtra(getString(R.string.text_username))
        binding.tvPlayerName.text = username
        binding.ivIcPlayerRock.setOnClickListener {
            if (!isGameFinished) {
                Log.d(TAG, "Player Memilih Batu")
                binding.ivIcPlayerRock.setBackgroundResource(R.drawable.bg_action_button)
                binding.ivIcPlayerPaper.isClickable = false
                binding.ivIcPlayerScissor.isClickable = false
                player = 0
                playerSelect = "Player Select Rock"
            }
        }
        binding.ivIcPlayerPaper.setOnClickListener {
            if (!isGameFinished) {
                Log.d(TAG, getString(R.string.text_player_select_paper))
                binding.ivIcPlayerPaper.setBackgroundResource(R.drawable.bg_action_button)
                binding.ivIcPlayerRock.isClickable = false
                binding.ivIcPlayerScissor.isClickable = false
                player = 1
                playerSelect = getString(R.string.text_player_select_paper)
            }
        }
        binding.ivIcPlayerScissor.setOnClickListener {
            if (!isGameFinished) {
                Log.d(TAG, getString(R.string.text_player_select_scissor))
                binding.ivIcPlayerRock.isClickable = false
                binding.ivIcPlayerPaper.isClickable = false
                binding.ivIcPlayerScissor.setBackgroundResource(R.drawable.bg_action_button)
                player = 2
                playerSelect = getString(R.string.text_player_select_scissor)
            }
        }
        binding.ivIcRefresh.setOnClickListener {
            Log.d(TAG, getString(R.string.text_reset_game))
            if (isGameFinished) {
                isGameFinished = false
                resetGame()
            }
        }
        binding.ivIcExit.setOnClickListener {
            Log.d(TAG, getString(R.string.text_exit))
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        }
        binding.tvStartBattle.setOnClickListener {
            computer = Random.nextInt(0, 3)
            if (computer == 0){
                computerSelect = getString(R.string.text_computer_select_rock)
            }
            else if (computer ==1){
                computerSelect = getString(R.string.text_computer_select_paper)
            } else {
                computerSelect = getString(R.string.text_computer_select_scissor)
            }
            inputComputer()

            if ((player.plus(1)).mod(3) == computer) {
                val result = getString(R.string.text_lose)
                val username = "Computer"
                val computerchoose = "$computerSelect"
                val playerchoose = "$playerSelect"
                Log.d(TAG, "Computer WIN")
                resultDialog(result, username,computerchoose, playerchoose)
            } else if (player == computer) {
                val result = getString(R.string.text_draw)
                val username = " "
                val computerchoose = "$computerSelect"
                val playerchoose = "$playerSelect"
                Log.d(TAG, "Hasil DRAW")
                resultDialog(result, username,computerchoose, playerchoose)
            } else {
                val result = getString(R.string.text_win)
                val username = "PLAYER"
                val computerchoose = "$computerSelect"
                val playerchoose = "$playerSelect"
                Log.d(TAG, "Player Menang")
                resultDialog(result, username,computerchoose, playerchoose)
            }
            isGameFinished = true
            binding.ivIcRefresh.setImageResource(R.drawable.ic_refresh_red)
        }
    }

    private fun resetGame() {
        isGameFinished = false
        player = -1
        computer = -1
        binding.ivIcRefresh.setImageResource(0)
        binding.ivIcPlayerScissor.isClickable = true
        binding.ivIcPlayerPaper.isClickable = true
        binding.ivIcPlayerRock.isClickable = true
        refreshGameState()
    }

    private fun refreshGameState() {
        binding.ivIcComputerScissor.setBackgroundResource(0)
        binding.ivIcComputerRock.setBackgroundResource(0)
        binding.ivIcComputerPaper.setBackgroundResource(0)
        binding.ivIcPlayerScissor.setBackgroundResource(0)
        binding.ivIcPlayerRock.setBackgroundResource(0)
        binding.ivIcPlayerPaper.setBackgroundResource(0)
    }

    private fun inputComputer() {
        when (GameCharacter.fromInt(computer)) {
            GameCharacter.ROCK -> {
                Log.d(TAG, "Computer Memilih Batu")
                binding.ivIcComputerRock.setBackgroundResource(R.drawable.bg_action_button)
                binding.ivIcComputerScissor.setBackgroundResource(0)
                binding.ivIcComputerPaper.setBackgroundResource(0)
            }
            GameCharacter.PAPER -> {
                Log.d(TAG, "Computer Memilih Kertas")
                binding.ivIcComputerRock.setBackgroundResource(0)
                binding.ivIcComputerScissor.setBackgroundResource(0)
                binding.ivIcComputerPaper.setBackgroundResource(R.drawable.bg_action_button)
            }
            GameCharacter.SCISSOR -> {
                Log.d(TAG, "Computer Memilih Gunting")
                binding.ivIcComputerRock.setBackgroundResource(0)
                binding.ivIcComputerScissor.setBackgroundResource(R.drawable.bg_action_button)
                binding.ivIcComputerPaper.setBackgroundResource(0)
            }
        }
    }

    fun resultDialog(result: String, username: String, computerchoose: String, playerchoose: String) {
        val username = intent.getStringExtra("username")
        val builder = AlertDialog.Builder(this)
        val binding = FragmentResultDialogBinding.inflate(layoutInflater)
        builder.setView(binding.root)
        val dialog = builder.create()
        binding.tvPlayer.text = username
        binding.tvGameResult.text = result
        binding.tvComputerChoose.text = computerchoose
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
}