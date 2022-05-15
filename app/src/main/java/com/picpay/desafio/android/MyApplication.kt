package com.picpay.desafio.android

import android.app.Application
import androidx.room.Room
import com.picpay.desafio.android.retrofit.config.RetrofitClient
import com.picpay.desafio.android.room.AppDatabase


class MyApplication: Application() {
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        RetrofitClient.setupRetrofit(applicationContext)
        AppDatabase.setupDatabase(applicationContext)
    }
}