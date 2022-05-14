package com.picpay.desafio.android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.tools.Result
import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.domain.usecases.GetUsersUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: GetUsersUseCase): ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val _state = MutableLiveData<State>(State.LOADING)
    val state: LiveData<State> get() = _state

    fun getContacts(){
        viewModelScope.launch {
            when(val result = useCase()){
                is Result.Success -> {
                    _users.postValue(result.value)
                    _state.postValue(State.SUCCESS)
                }
                is Result.Failure -> { _state.postValue(State.FAILURE) }
            }
        }
    }
}