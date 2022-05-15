package com.picpay.desafio.android.room.repositories

import android.util.Log
import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.domain.repositories.LocalUserRepository
import com.picpay.desafio.android.room.daos.UserDAO
import com.picpay.desafio.android.room.mappers.UserMapper
import com.picpay.desafio.android.tools.Result

class LocalUserRepositoryImpl(private val dao: UserDAO): LocalUserRepository{
    override suspend fun getUser(): Result<List<User>> {
        Log.d("CACHE_FEATURE", "Response from ROOM")
        return try {
            val models = dao.getAll()
            when(models.isEmpty()){
                false -> Result.Success(UserMapper.fromDatabase(models))
                true -> Result.Failure(Throwable("Error"))
            }
        } catch (t: Throwable){
            Result.Failure(t)
        }
    }

    override suspend fun refresh(users: List<User>) {
        Log.d("CACHE_FEATURE", "Save users in ROOM")
        try {
            val models = UserMapper.toDatabase(users)
            dao.insert(models)
        } catch (t: Throwable){
            // Send error to analytics
        }
    }
}