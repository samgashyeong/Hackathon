package com.example.hackathon.screen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.hackathon.R
import com.example.hackathon.screen.login.LoginActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val pref = getSharedPreferences("isFirst", MODE_PRIVATE)
        val first = pref.getBoolean("isFirst", false)

//        ServerClient.getApiService().update(
//            TimeUpdate(
//                nameDefine,
//                SingleTon.phoneDefine
//            )
//        ).enqueue(object : Callback<Success> {
//
//            override fun onResponse(call: Call<Success>, response: Response<Success>) {
//                if(response.isSuccessful){
//                    Log.d(ContentValues.TAG, "onResponse: time성공")
//                }
//            }
//
//            override fun onFailure(call: Call<Success>, t: Throwable) {
//                Log.d(ContentValues.TAG, "onFailure: time실패")
//            }
//
//        })


        Handler(Looper.getMainLooper()).postDelayed({ //스플래쉬
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1500)
//        if (first == false) {
//            Log.d("Is first Time?", "first")
//            val editor = pref.edit()
//            editor.putBoolean("isFirst", true)
//            editor.commit()
//            Handler(Looper.getMainLooper()).postDelayed({ //스플래쉬
//                startActivity(Intent(this, LoginActivity::class.java))
//                finish()
//            }, 1500)
//            //앱 최초 실행시 하고 싶은 작업
//        } else {
//            Handler(Looper.getMainLooper()).postDelayed({ //스플래쉬
//                startActivity(Intent(this, MainActivity::class.java))
//                finish()
//            }, 1500)
//        }
    }
}