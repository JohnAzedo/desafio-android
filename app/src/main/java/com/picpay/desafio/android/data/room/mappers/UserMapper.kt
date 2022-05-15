package com.picpay.desafio.android.data.room.mappers

import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.data.room.models.UserModel

object UserMapper {

    fun toDatabase(users: List<User>): List<UserModel> {
        return users.map {
            UserModel(
                id = it.id,
                img = it.img,
                username = it.username,
                name = it.name
            )
        }
    }

    fun fromDatabase(users: List<UserModel>): List<User>{
        return users.map {
            User(
                id = it.id,
                img = it.img ?: "",
                username = it.username ?: "",
                name = it.name ?: ""
            )
        }
    }
}