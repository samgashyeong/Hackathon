package com.example.hackathon.api

import com.example.hackathon.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    //ex
    //@POST("뭐시ㅣ기뭐시ㅣ")
    //    fun joinRequest(@Body 모델이름변수: 모델클래스이름): Call<모델클래스이름>

    @POST("/api/auth/register/local")
    fun register(@Body user: User): Call<Success>

    @POST("/api/auth/login/local")
    fun login(@Body loginUser : LoginUser) : Call<Success>

    @POST("/api/auth/time/start")
    fun start(@Body timeUpdate: TimeUpdate) : Call<Success>

    @POST("/api/auth/time/end")
    fun end(@Body timeUpdate: TimeUpdate2) : Call<Success>

    @POST("/api/auth/tt")
    fun tt(@Body success: Success)

}