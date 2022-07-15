package com.example.hackathon.api

import com.example.hackathon.data.LoginUser
import com.example.hackathon.data.Success
import com.example.hackathon.data.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    //ex
    //@POST("뭐시ㅣ기뭐시ㅣ")
    //    fun joinRequest(@Body 모델이름변수: 모델클래스이름): Call<모델클래스이름>

    @POST("/api/auth/register/local")
    fun register(@Body user : User): Call<Success>

    @POST("/api/auth/login/local")
    fun login(@Body loginUser : LoginUser) : Call<Success>


}