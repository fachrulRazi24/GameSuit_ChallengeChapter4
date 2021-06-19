package com.example.binaracademy.gamesuit_challengechapter4.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.binaracademy.gamesuit_challengechapter4.R
import com.example.binaracademy.gamesuit_challengechapter4.databinding.ActivityMainBinding
import com.example.binaracademy.gamesuit_challengechapter4.enum.GameCharacter
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding

    private var player: Int = -1
    private var computer: Int = -1
    private var isGameFinished: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        bindViews()
        setClickEvent()
    }

    private fun bindViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setClickEvent() {
        binding.ivIcPlayerRock.setOnClickListener {
            Log.d(TAG, "Player Memilih Batu")
            player = 0
            inputPlayer()
            randomizeComputer()
            startGame()
        }
        binding.ivIcPlayerPaper.setOnClickListener {
            Log.d(TAG, "Player Memilih Kertas")
            player = 1
            inputPlayer()
            randomizeComputer()
            startGame()
        }
        binding.ivIcPlayerScissor.setOnClickListener {
            Log.d(TAG, "Player Memilih Gunting")
            player = 2
            inputPlayer()
            randomizeComputer()
            startGame()
        }
        binding.ivIcRefresh.setOnClickListener {
            Log.d(TAG, "Reset Game, Enjoy !")
            isGameFinished = true
            resetGame()
        }
    }

    private fun resetGame() {
        isGameFinished = false
        player = -1
        computer = -1
        binding.ivIcVs.setImageResource(R.drawable.ic_vs)
        binding.ivIcRefresh.setImageResource(0)
        refreshGameState()
    }

    private fun refreshGameState (){
        binding.ivIcComputerScissor.setBackgroundResource(0)
        binding.ivIcComputerRock.setBackgroundResource(0)
        binding.ivIcComputerPaper.setBackgroundResource(0)
        binding.ivIcPlayerScissor.setBackgroundResource(0)
        binding.ivIcPlayerRock.setBackgroundResource(0)
        binding.ivIcPlayerPaper.setBackgroundResource(0)
    }

    private fun startGame() {
        if ((player.plus(1)).mod(3) == computer) {
            Log.d(TAG, "Computer Menang")
            binding.ivIcVs.setImageResource(R.drawable.ic_result_computer_win)
        } else if (player == computer) {
            Log.d(TAG, "Hasil DRAW")
            binding.ivIcVs.setImageResource(R.drawable.ic_result_draw)
        } else {
            Log.d(TAG, "Player Menang")
            binding.ivIcVs.setImageResource(R.drawable.ic_result_player_win)
        }
        isGameFinished = true
        binding.ivIcRefresh.setImageResource(R.drawable.ic_refresh_white)
    }

    private fun randomizeComputer() {
        computer = Random.nextInt(0, 3)
        inputComputer()
    }

    private fun inputPlayer() {
        when (GameCharacter.fromInt(player)) {
            GameCharacter.ROCK -> {
                binding.ivIcPlayerRock.setBackgroundResource(R.drawable.bg_action_button)
                binding.ivIcPlayerPaper.setBackgroundResource(0)
                binding.ivIcPlayerScissor.setBackgroundResource(0)
            }
            GameCharacter.PAPER -> {
                binding.ivIcPlayerRock.setBackgroundResource(0)
                binding.ivIcPlayerPaper.setBackgroundResource(R.drawable.bg_action_button)
                binding.ivIcPlayerScissor.setBackgroundResource(0)
            }
            GameCharacter.SCISSOR -> {
                binding.ivIcPlayerRock.setBackgroundResource(0)
                binding.ivIcPlayerScissor.setBackgroundResource(R.drawable.bg_action_button)
                binding.ivIcPlayerPaper.setBackgroundResource(0)
            }
        }
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
}