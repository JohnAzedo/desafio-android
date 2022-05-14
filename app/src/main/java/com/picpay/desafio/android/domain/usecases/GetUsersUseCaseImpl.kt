package com.picpay.desafio.android.domain.usecases

import com.picpay.desafio.android.tools.Result
import com.picpay.desafio.android.domain.repositories.UserRepository
import com.picpay.desafio.android.domain.entities.User

class GetUsersUseCaseImpl(private val repository: UserRepository): GetUsersUseCase {
    override suspend fun invoke(): Result<List<User>> {
        return repository.getContacts()
    }
}