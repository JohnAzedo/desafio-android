package com.picpay.desafio.android.data.retrofit.repositories


import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.domain.repositories.RemoteUserRepository
import com.picpay.desafio.android.data.retrofit.config.ServiceResult
import com.picpay.desafio.android.data.retrofit.config.parseResponse
import com.picpay.desafio.android.data.retrofit.config.HttpErrors
import com.picpay.desafio.android.data.retrofit.mappers.UserMapper
import com.picpay.desafio.android.data.retrofit.services.PicPayService
import com.picpay.desafio.android.tools.Result

class RemoteUserRepositoryImpl(private val service: PicPayService): RemoteUserRepository {
    override suspend fun getUsers(): Result<List<User>> {
        return try {
            when (val result = service.getUsers().parseResponse()){
                is ServiceResult.Success -> Result.Success(UserMapper.make(result.value))
                is ServiceResult.Failure -> Result.Failure(HttpErrors.StatusCodeInvalid(result.statusCode))
                else -> Result.Failure(HttpErrors.Unknown())
            }
        } catch (e: Throwable) {
            Result.Failure(e)
        }
    }
}