package com.picpay.desafio.android.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.picpay.desafio.android.domain.usecases.GetUsersUseCaseImpl
import com.picpay.desafio.android.retrofit.config.RetrofitClient
import com.picpay.desafio.android.retrofit.repositories.RemoteUserRepositoryImpl
import com.picpay.desafio.android.retrofit.services.PicPayService
import com.picpay.desafio.android.room.AppDatabase
import com.picpay.desafio.android.room.repositories.LocalUserRepositoryImpl
import com.picpay.desafio.android.ui.MainViewModel

object MainViewModelFactory {
    private class Factory: ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val service = RetrofitClient.buildService(PicPayService::class.java)
            val dao = AppDatabase.getUserDao()
            val localRepository = LocalUserRepositoryImpl(dao)
            val remoteRepository = RemoteUserRepositoryImpl(service)
            val useCase = GetUsersUseCaseImpl(remoteRepository, localRepository)
            return MainViewModel(useCase) as T
        }
    }

    fun AppCompatActivity.make(): MainViewModel {
        return ViewModelProvider(this, Factory())[MainViewModel::class.java]
    }
}