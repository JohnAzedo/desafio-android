package com.picpay.desafio.android.domain.repositories

import com.picpay.desafio.android.tools.Result
import com.picpay.desafio.android.domain.entities.User

interface RemoteUserRepository {
    suspend fun getUsers(): Result<List<User>>
}