package com.picpay.desafio.android.data.repositories

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.retrofit.config.HttpErrors
import com.picpay.desafio.android.data.retrofit.repositories.RemoteUserRepositoryImpl
import com.picpay.desafio.android.data.retrofit.services.PicPayService
import com.picpay.desafio.android.domain.repositories.RemoteUserRepository
import com.picpay.desafio.android.tools.Result
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.net.SocketTimeoutException

class RemoteUserRepositoryImplTest {

    private lateinit var repository: RemoteUserRepository
    private lateinit var server: MockWebServer

    @Before
    fun setup() {
        server = MockWebServer()
        val gson = GsonConverterFactory.create(GsonBuilder().setLenient().create())
        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(gson)
            .build()

        val service = retrofit.create(PicPayService::class.java)
        repository = RemoteUserRepositoryImpl(service)
    }

    @After
    fun stopServer() {
        server.shutdown()
    }

    @Test
    fun `Given a valid json when getUser was called then return a valid result with users list`(){
        enqueueResponse("users.json")
        runBlocking {
            val result = when(val result = repository.getUsers()){
                is Result.Success -> result.value
                else -> listOf()
            }
            assertEquals(50, result.size)
        }
    }

    @Test
    fun `Given a invalid status code when getUser was called return a Failure with a mapped throwable`(){
        server.enqueue(MockResponse().setResponseCode(500).setBody(""))
        runBlocking {
            val result = repository.getUsers() as Result.Failure
            assertEquals(HttpErrors.StatusCodeInvalid(500).cause, result.error.cause)
        }
    }

    @Test(expected = SocketTimeoutException::class)
    fun `Given a server timeout when getUser was called return a Failure with SocketTimeoutException`(){
        runBlocking {
            val result = repository.getUsers() as Result.Failure
            throw result.error
        }
    }

    private fun enqueueResponse(filename: String, headers: Map<String, String> = emptyMap()) {
        val path = "src/test/java/com/picpay/desafio/android/data/repositories/resources/$filename"
        val file = File(path).readText(Charsets.UTF_8)
        val mockResponse = MockResponse()
        for ((key, value) in headers) { mockResponse.addHeader(key, value) }
        server.enqueue(mockResponse.setBody(file))
    }
}
