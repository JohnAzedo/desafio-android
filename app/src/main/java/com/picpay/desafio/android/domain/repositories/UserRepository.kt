package com.picpay.desafio.android.domain.repositories

import com.picpay.desafio.android.tools.Result
import com.picpay.desafio.android.domain.entities.User

interface UserRepository {
    suspend fun getContacts(): Result<List<User>>
}