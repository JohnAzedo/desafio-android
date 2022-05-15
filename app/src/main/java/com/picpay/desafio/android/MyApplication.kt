package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.data.retrofit.config.RetrofitClient
import com.picpay.desafio.android.data.room.config.AppDatabase


class MyApplication: Application() {
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        RetrofitClient.setupRetrofit(applicationContext)
        AppDatabase.setupDatabase(applicationContext)
    }
}