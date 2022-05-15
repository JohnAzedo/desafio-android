package com.picpay.desafio.android.data.retrofit.services

import com.picpay.desafio.android.data.retrofit.responses.UserResponse
import retrofit2.Response
import retrofit2.http.GET


interface PicPayService {
    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>
}