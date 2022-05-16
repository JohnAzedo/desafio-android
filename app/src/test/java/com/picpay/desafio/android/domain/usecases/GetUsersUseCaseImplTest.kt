package com.picpay.desafio.android.domain.usecases

import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.domain.repositories.LocalUserRepository
import com.picpay.desafio.android.domain.repositories.RemoteUserRepository
import com.picpay.desafio.android.tools.Result
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetUsersUseCaseImplTest {

    private lateinit var useCase: GetUsersUseCase

    @Mock
    lateinit var remoteRepository: RemoteUserRepository

    @Mock
    lateinit var localRepository: LocalUserRepository

    @Before
    fun setup(){
        useCase = GetUsersUseCaseImpl(remoteRepository, localRepository)
    }

    @Test
    fun `Given a remoteRepository failure when localRepository success then return a success with cache data`() {
        runBlocking {
            Mockito.`when`(remoteRepository.getUsers()).thenReturn(FAILURE_REMOTE_RETURN)
            Mockito.`when`(localRepository.getUser()).thenReturn(SUCCESS_LOCAL_RETURN)

            val result = when(val result = useCase()){
                is Result.Success -> result.value
                else -> listOf()
            }
            assert(result.isNotEmpty())
        }
    }

    companion object {
        private val USERS_LIST = listOf(
            User(id = 1, username = "JohnAzedo", name = "João Pedro", img = "img")
        )

        val FAILURE_REMOTE_RETURN = Result.Failure(Throwable("Http error returned!"))
        val SUCCESS_LOCAL_RETURN = Result.Success(USERS_LIST)
    }
}