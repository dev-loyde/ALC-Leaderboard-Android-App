package com.devloyde.lidboard.networking

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkServiceBuilder {
    const val BOARD_BASE_URL = "https://gadsapi.herokuapp.com"
    private const val PROJECT_BASE_URL = "https://docs.google.com/forms/d/e/"
    private var baseUrl: String? = null

    private val httpClient = OkHttpClient.Builder()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl!!)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    fun<T> buildService(url:String,service: Class<T>):T{
        baseUrl = url
        return retrofit.create(service)
    }


}
