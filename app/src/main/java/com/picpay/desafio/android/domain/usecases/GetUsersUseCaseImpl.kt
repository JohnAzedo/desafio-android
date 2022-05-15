package com.picpay.desafio.android.domain.usecases

import com.picpay.desafio.android.tools.Result
import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.domain.repositories.LocalUserRepository
import com.picpay.desafio.android.domain.repositories.RemoteUserRepository

class GetUsersUseCaseImpl(private val remoteRepository: RemoteUserRepository,
                          private val localRepository: LocalUserRepository): GetUsersUseCase {
    override suspend fun invoke(): Result<List<User>> {
        return when(val result = remoteRepository.getUsers()){
            is Result.Success -> {
                localRepository.refresh(result.value)
                result
            }
            is Result.Failure -> localRepository.getUser()
            else -> result
        }
    }
}