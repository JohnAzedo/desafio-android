package com.picpay.desafio.android.data.mappers

import com.picpay.desafio.android.data.retrofit.mappers.UserMapper
import com.picpay.desafio.android.data.retrofit.responses.UserResponse
import org.junit.Test

class UserMapperTest {

    @Test
    fun `Given 2 UserResponses with one invalid data when make() was called then return a list of User with object`(){
        val result = UserMapper.make(LIST_OF_USERS)
        assert(result[0].id == 1)
    }

    companion object {
        val LIST_OF_USERS = listOf(
            UserResponse(
                id = null,
                name = "John",
                username = "JohnAzedo",
                img = null
            ),
            UserResponse(
                id = 1,
                name = "Teste",
                username = "Teste2",
                img = "uma imagem"
            )
        )
    }
}