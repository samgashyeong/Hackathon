package com.example.hackathon.screen.quiz

import com.example.hackathon.R
import java.util.*

class QuizHelper {
    fun randomInitialORCalculation(): Int {
        val random = Random()
        return random.nextInt(2)
    }
    // 0 is initial_sound
    // 1 is calculation

}