package com.cnm.naverhomework.network

import com.cnm.naverhomework.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkHelper {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        })
        .addInterceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", "D9_uu75G1uJiKwd4bNrb")
                .addHeader("X-Naver-Client-Secret", "eBuWaNbn92")
                .build()
            it.proceed(request)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://openapi.naver.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val naverApi = retrofit.create(NaverApi::class.java)


}