package com.picpay.desafio.android.retrofit.config

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object RetrofitClient {
    private val client = OkHttpClient.Builder().build()
    private val gson = GsonConverterFactory.create(GsonBuilder().create())
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(gson)
        .client(client).build()

    fun<T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}