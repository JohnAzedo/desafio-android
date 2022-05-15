package com.picpay.desafio.android.data.retrofit.config.interceptors

import android.util.Log
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class NetworkInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("CACHE_FEATURE", "Save data in cache")

        val response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(CACHE_MAX_AGE, TimeUnit.SECONDS)
            .build()

        return response.newBuilder()
            .removeHeader(HEADER_PRAGMA)
            .removeHeader(HEADER_CACHE_CONTROL)
            .header(HEADER_CACHE_CONTROL, cacheControl.toString())
            .build()
    }

    companion object {
        const val CACHE_MAX_AGE = 10
        const val HEADER_PRAGMA = "Pragma"
        const val HEADER_CACHE_CONTROL = "Cache-Control"
    }
}