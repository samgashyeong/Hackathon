package com.example.hackathon.screen

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.telephony.TelephonyManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.hackathon.BatteryCheck
import com.example.hackathon.R
import com.example.hackathon.screen.login.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn.hasPermissions
import java.util.jar.Manifest


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val pref = getSharedPreferences("isFirst", MODE_PRIVATE)
        val first = pref.getBoolean("isFirst", false)

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