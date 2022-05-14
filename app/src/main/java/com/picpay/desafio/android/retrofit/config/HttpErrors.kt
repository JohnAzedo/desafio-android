package com.picpay.desafio.android.retrofit.config

object HttpErrors {
    class StatusCodeInvalid(statusCode: Int): Throwable("Http request status code is $statusCode")
    class Unknown: Throwable("Http request unknown error")
}