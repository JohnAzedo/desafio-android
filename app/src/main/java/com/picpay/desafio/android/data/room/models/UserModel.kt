package com.picpay.desafio.android.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserModel(
    @PrimaryKey val id: Int,
    val img: String?,
    val name: String?,
    val username: String?
)