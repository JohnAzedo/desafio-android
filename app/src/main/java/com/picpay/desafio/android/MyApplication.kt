package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.retrofit.config.RetrofitClient


class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        RetrofitClient.setupRetrofit(applicationContext)
    }
}