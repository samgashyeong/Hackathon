package com.example.hackathon.screen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.hackathon.R


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val pref = getSharedPreferences("isFirst", MODE_PRIVATE)
        val first = pref.getBoolean("isFirst", false)
        if (first == false) {
            Log.d("Is first Time?", "first")
            val editor = pref.edit()
            editor.putBoolean("isFirst", true)
            editor.commit()
            Handler(Looper.getMainLooper()).postDelayed({ //스플래쉬
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 1500)
            //앱 최초 실행시 하고 싶은 작업
        } else {
            Log.d("Is first Time?", "not first")
        }
    }
}