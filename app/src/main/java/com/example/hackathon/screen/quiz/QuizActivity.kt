package com.example.hackathon.screen.quiz

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.hackathon.R
import com.example.hackathon.SingleTon
import com.example.hackathon.api.ServerClient
import com.example.hackathon.data.TimeUpdate
import com.example.hackathon.databinding.ActivityQuizBinding
import java.util.*
import com.example.hackathon.data.Success
import com.example.hackathon.data.TimeUpdate2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuizBinding
    var quizLevel = 0
    var arrayNum: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz)
        binding.level = quizLevel

        arrayNum = Random().nextInt(5)
        var initialORCalculation: Int
        initialORCalculation = QuizHelper().randomInitialORCalculation()
        binding.quiz = when (initialORCalculation) {
            0 -> {
                toInitialSoundQuiz()
            }
            1 -> {
                toCalculationQuiz()
            }
            else -> {
                null
            }
        }
        1
        binding.quizBtn1.setOnClickListener {

            if (quizLevel == 2) {
                finish()
                val intent = Intent(this, QuizSuccessActivity::class.java)
                startActivity(intent)
            } else {

                val etvText = binding.answerEtv1.text.toString()
                val answer = when (initialORCalculation) {
                    0 -> {
                        toInitialAnswer()
                    }
                    1 -> {
                        toCalculationAnswer()
                    }
                    else -> {
                        null
                    }
                }

                if (etvText != "") {

                    if (etvText == answer) {


                        if (binding.errorTv1.visibility == View.VISIBLE) {
                            binding.errorTv1.visibility = View.GONE
                        }

                        quizLevel++
                        initialORCalculation = QuizHelper().randomInitialORCalculation()
                        val quiz by lazy {
                            when (initialORCalculation) {
                                0 -> {
                                    toInitialSoundQuiz()
                                }
                                1 -> {
                                    toCalculationQuiz()
                                }
                                else -> {

                                }
                            }
                        }


                        binding.quiz = quiz.toString()
                        binding.answerEtv1.setText("")
                    } else {
                        binding.errorTv1.visibility = View.VISIBLE
                    }
                } else {
                    binding.errorTv1.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun toInitialSoundQuiz(): String {
        val quizInitialSoundLv1Array =
            resources.getStringArray(R.array.quiz_initial_sound_lv1_array)
        val quizInitialSoundLv2Array =
            resources.getStringArray(R.array.quiz_initial_sound_lv2_array)
        val quizInitialSoundLv3Array =
            resources.getStringArray(R.array.quiz_initial_sound_lv3_array)
        var array: Array<String>? = null
        when (quizLevel) {
            0 -> array = quizInitialSoundLv1Array
            1 -> array = quizInitialSoundLv2Array
            2 -> array = quizInitialSoundLv3Array
            3 -> array = quizInitialSoundLv1Array
        }

        return array!![arrayNum]
    }

    private fun toCalculationQuiz(): String {
        val quizCalculationLv1Array = resources.getStringArray(R.array.quiz_calculation_lv1_array)
        val quizCalculationLv2Array = resources.getStringArray(R.array.quiz_calculation_lv2_array)
        val quizCalculationLv3Array = resources.getStringArray(R.array.quiz_calculation_lv3_array)
        var array: Array<String>? = null
        when (quizLevel) {
            0 -> array = quizCalculationLv1Array
            1 -> array = quizCalculationLv2Array
            2 -> array = quizCalculationLv3Array
            3 -> array = quizCalculationLv1Array
        }

        return array!![arrayNum]
    }

    private fun toInitialAnswer(): String {
        val quizInitialSoundLv1Array =
            resources.getStringArray(R.array.quiz_initial_sound_lv1_answer_array)
        val quizInitialSoundLv2Array =
            resources.getStringArray(R.array.quiz_initial_sound_lv2_answer_array)
        val quizInitialSoundLv3Array =
            resources.getStringArray(R.array.quiz_initial_sound_lv3_answer_array)
        var array: Array<String>? = null
        when (quizLevel) {
            0 -> array = quizInitialSoundLv1Array
            1 -> array = quizInitialSoundLv2Array
            2 -> array = quizInitialSoundLv3Array
            3 -> array = quizInitialSoundLv1Array
        }

        return array!![arrayNum]
    }

    private fun toCalculationAnswer(): String {
        val quizCalculationLv1Array =
            resources.getStringArray(R.array.quiz_calculation_lv1_answer_array)
        val quizCalculationLv2Array =
            resources.getStringArray(R.array.quiz_calculation_lv2_answer_array)
        val quizCalculationLv3Array =
            resources.getStringArray(R.array.quiz_calculation_lv3_answer_array)
        var array: Array<String>? = null
        when (quizLevel) {
            0 -> array = quizCalculationLv1Array
            1 -> array = quizCalculationLv2Array
            2 -> array = quizCalculationLv3Array
            3 -> array = quizCalculationLv3Array
        }

        return array!![arrayNum]
    }

    override fun onStart() {
        super.onStop()
        ServerClient.getApiService().end(
            TimeUpdate2(
                "01088016084"
            )
        ).enqueue(object : Callback<Success> {

            override fun onResponse(call: Call<Success>, response: Response<Success>) {
                if(response.isSuccessful){
                    Log.d(TAG, "onResponse: 성공!!!!!!")
                }
            }

            override fun onFailure(call: Call<Success>, t: Throwable) {
                Log.d(TAG, "onFailure: 실패!!!!!!")
            }

        })
    }

    override fun onStop() {
        super.onStart()

        ServerClient.getApiService().start(
            TimeUpdate(
                "정지영",
                "01088016084"
            )
        ).enqueue(object : Callback<Success> {

            override fun onResponse(call: Call<Success>, response: Response<Success>) {
                if(response.isSuccessful){
                    Log.d(TAG, "onResponse: 성공!!!!!!")
                }
            }

            override fun onFailure(call: Call<Success>, t: Throwable) {
                Log.d(TAG, "onFailure: 실패!!!!!!")
            }

        })
    }
}