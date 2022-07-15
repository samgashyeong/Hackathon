package com.example.hackathon.screen.login

import android.Manifest
import android.Manifest.permission.*
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.hackathon.R
import com.example.hackathon.api.ServerClient
import com.example.hackathon.data.LoginUser
import com.example.hackathon.data.Success
import com.example.hackathon.data.User
import com.example.hackathon.databinding.ActivityRegisterBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn.hasPermissions
import io.grpc.Server
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
import java.util.regex.Pattern


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var phoneNumber : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val shared = getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = shared.edit()
        chkPermission()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.registerBtn.setOnClickListener {
            if(binding.pwEt.text.isBlank() or binding.nameEt.text.isBlank()){
                Toast.makeText(this, "빈칸을 입력하셔야되요!!", Toast.LENGTH_SHORT).show()
            }
            else{
                ServerClient.getApiService().register(
                    User(
                        binding.nameEt.text.toString(),
                        phoneNumber,
                        "119",
                        binding.pwEt.text.toString()
                    )
                ).enqueue(object : Callback<Success> {

                    override fun onResponse(call: Call<Success>, response: Response<Success>) {
                        if(response.isSuccessful){
                            login()
                            Log.d(TAG, "onResponse: 성공!!!!!!")
                        }
                    }

                    override fun onFailure(call: Call<Success>, t: Throwable) {
                        Log.d(TAG, "onFailure: 실패!!!!!!")
                    }

                })
//                editor.putString("name", binding.nameEt.text.toString())
//                editor.putString("pw", binding.pwEt.text.toString())
//                editor.apply()
            }

        }
    }

    private fun login() {
        ServerClient.getApiService().login(LoginUser(binding.nameEt.text.toString(), phoneNumber, binding.pwEt.text.toString()))
            .enqueue(object : Callback<Success> {
                override fun onResponse(call: Call<Success>, response: Response<Success>) {
                    if(response.isSuccessful){
                        Log.d(TAG, "onResponse: 성공!!")
                    }
                }

                override fun onFailure(call: Call<Success>, t: Throwable) {
                    Log.d(TAG, "onFailure: 실패!!")
                }

            })
    }

    @SuppressLint("ServiceCast", "MissingPermission")
    fun getPhoneNumber(): String {
        var tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return tm.line1Number
    }
    fun chkPermission(): Boolean {
        // 위험 권한을 모두 승인했는지 여부
        var mPermissionsGranted = false
        // 승인 받기 위한 권한 목록
        val mRequiredPermissions = arrayOf<String>(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_PHONE_NUMBERS
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 필수 권한을 가지고 있는지 확인한다.

            Log.d(TAG, "chkPermission: 엄준식 식식식")
            val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)

            val permission2 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS)

            // 필수 권한 중에 한 개라도 없는 경우
            if (permission == PackageManager.PERMISSION_DENIED ||
                    permission2 == PackageManager.PERMISSION_DENIED) {
                // 권한을 요청한다.
                ActivityCompat.requestPermissions(
                    this@RegisterActivity,
                    mRequiredPermissions,100
                )
            }
        } else {
            mPermissionsGranted = true
        }
        phoneNumber = getPhoneNumber()
        return mPermissionsGranted
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 100){
            var check_result = true

            for(i in grantResults){
                if(i != PackageManager.PERMISSION_GRANTED){
                    check_result = false;
                    break;
                }
            }

            if(check_result == true){
                Log.d(TAG, "onRequestPermissionsResult: 승인됨!")
                phoneNumber = getPhoneNumber()
                Log.d(TAG, "onRequestPermissionsResult: ${phoneNumber}")
            }
            else{
                finish()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}