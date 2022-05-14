package com.picpay.desafio.android.tools

interface Result <out T> {
    data class Success<T> (val value: T): Result<T>
    data class Failure(val error: Throwable): Result<Nothing>
}
