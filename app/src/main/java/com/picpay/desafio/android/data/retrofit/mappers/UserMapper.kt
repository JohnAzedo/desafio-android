package com.picpay.desafio.android.data.retrofit.mappers

import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.data.retrofit.responses.UserResponse

object UserMapper {
    fun make(responses: List<UserResponse>): List<User> {
        val users: List<User> = responses
            .filter { it.isNotNull() }
            .map {
                User(
                    id = it.id!!,
                    name = it.name!!,
                    img = it.img!!,
                    username = it.username!!
                )
            }

        return users
    }
}