package com.picpay.desafio.android.data.retrofit.config

import android.util.Log
import retrofit2.Response
import java.net.HttpURLConnection

interface ServiceResult <out Response> {
    data class Success<Response> (val value: Response): ServiceResult<Response>
    data class Failure(val statusCode: Int): ServiceResult<Nothing>
}

/**
 * When response returns this method get it from
 * request and check if is successful, if not, parseResponse
 * will send a failure with a HTTP status code.
 */
fun <R: Any> Response<R>.parseResponse() = run {
    val body = body()

    if(raw().networkResponse != null ){
        Log.d("CACHE_FEATURE", "Response from NETWORK")
    }

    if(raw().cacheResponse != null && raw().networkResponse == null) {
        Log.d("CACHE_FEATURE", "Response from CACHE")
    }

    when {
        isSuccessful && body != null ->
            ServiceResult.Success(body)

        isSuccessful && body == null ->
            ServiceResult.Failure(HttpURLConnection.HTTP_INTERNAL_ERROR)

        else ->
            ServiceResult.Failure(code())
    }
}

