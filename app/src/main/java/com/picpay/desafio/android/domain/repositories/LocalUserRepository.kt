package com.picpay.desafio.android.domain.repositories

import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.tools.Result

interface LocalUserRepository {
    suspend fun getUser(): Result<List<User>>
    suspend fun refresh(users: List<User>)
}