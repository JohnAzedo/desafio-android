package com.picpay.desafio.android.domain.usecases

import com.picpay.desafio.android.tools.Result
import com.picpay.desafio.android.domain.entities.User

interface GetUsersUseCase {
    suspend operator fun invoke(): Result<List<User>>
}