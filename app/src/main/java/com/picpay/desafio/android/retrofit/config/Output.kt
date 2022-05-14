package com.picpay.desafio.android.retrofit.config

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
    when {
        isSuccessful && body != null ->
            ServiceResult.Success(body)

        isSuccessful && body == null ->
            ServiceResult.Failure(HttpURLConnection.HTTP_INTERNAL_ERROR)

        else ->
            ServiceResult.Failure(code())
    }
}

