package com.devloyde.lidboard.networking

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkServiceBuilder {
    private const val boardBaseUrl = "https://gadsapi.herokuapp.com/"
    private const val projectBaseUrl = "https://docs.google.com/forms/d/e/"

    private val httpClient = OkHttpClient.Builder().addInterceptor(Interceptor(){
        val request = it.request()
            .newBuilder().addHeader("Subscription-Key","dcb377dcaa654d42852d79dd0b977247").build()
        return@Interceptor it.proceed(request)
    })



    fun<T> buildBoardService(service: Class<T>):T{
        return Retrofit.Builder()
            .baseUrl(boardBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build().create(service)
    }

    fun<T> buildProjectService(service: Class<T>):T{
        return Retrofit.Builder()
            .baseUrl(projectBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build().create(service)
    }

}
