package com.example.hackathon.api

import com.squareup.okhttp.Interceptor
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.internal.http.OkHeaders
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServerClient {

    private const val baseUrl = "https://md.khjcode.com/"
    var accessToken : String? = null

    fun getInstance() : Retrofit {

//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY 필요할시에 쓰기

        val interceptor: Interceptor = Interceptor { chain ->
            if (accessToken != null) {
                val request: Request = chain.request().newBuilder()
                    .addHeader("Authorization", accessToken)
                    .build()
                chain.proceed(request)
            } else chain.proceed(chain.request())
        }

        val client:  okhttp3.OkHttpClient= okhttp3.OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build()
        //19~20번줄 사용할 시에 .addInterceptor(loggingInterceptor).addInterceptor(interceptor) 붙여서 쓰기

        val instance : Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return instance!!
    }

    fun getApiService() : ApiService = getInstance().create(ApiService::class.java)
}