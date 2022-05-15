package com.picpay.desafio.android.retrofit.config

import android.app.Application
import android.content.Context
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.MyApplication
import com.picpay.desafio.android.retrofit.config.interceptors.NetworkInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import java.io.File

object RetrofitClient {
    private const val CACHE_SIZE: Long = 5 * 1024 * 1024 //5 MB
    private lateinit var retrofit: Retrofit

    fun<T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }

    fun setupRetrofit(context: Context) {
        val cache = Cache(File(context.cacheDir, "id"), CACHE_SIZE)
        val gson = GsonConverterFactory.create(GsonBuilder().create())
        val client = OkHttpClient.Builder()
            .cache(cache)
            .addNetworkInterceptor(NetworkInterceptor())
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gson)
            .client(client).build()
    }
}