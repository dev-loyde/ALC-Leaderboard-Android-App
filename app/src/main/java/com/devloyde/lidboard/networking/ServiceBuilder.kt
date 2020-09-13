package com.devloyde.lidboard.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkServiceBuilder {
    private const val boardBaseUrl = "https://gadsapi.herokuapp.com/"
    private const val projectBaseUrl = "https://docs.google.com/forms/d/e/"

    private val httpClient = OkHttpClient.Builder()

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
