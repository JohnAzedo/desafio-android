package com.picpay.desafio.android

import com.picpay.desafio.android.retrofit.services.PicPayService

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}