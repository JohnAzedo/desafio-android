package com.picpay.desafio.android.retrofit.repositories


import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.domain.repositories.UserRepository
import com.picpay.desafio.android.retrofit.config.ServiceResult
import com.picpay.desafio.android.retrofit.config.parseResponse
import com.picpay.desafio.android.retrofit.config.HttpErrors
import com.picpay.desafio.android.retrofit.mappers.UserMapper
import com.picpay.desafio.android.retrofit.services.PicPayService
import com.picpay.desafio.android.tools.Result

class UserRepositoryImpl(private val service: PicPayService): UserRepository {
    override suspend fun getContacts(): Result<List<User>> {
        return when (val result = service.getUsers().parseResponse()){
            is ServiceResult.Success -> Result.Success(UserMapper.make(result.value))
            is ServiceResult.Failure -> Result.Failure(HttpErrors.StatusCodeInvalid(result.statusCode))
            else -> Result.Failure(HttpErrors.Unknown())
        }
    }
}