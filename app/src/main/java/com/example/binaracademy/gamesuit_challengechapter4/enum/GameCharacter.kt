package com.example.binaracademy.gamesuit_challengechapter4.enum

enum class GameCharacter(val value: Int) {
    ROCK(0),
    PAPER(1),
    SCISSOR(2);

    companion object{
        fun fromInt(value: Int) = values().first(){it.value == value}
    }
}