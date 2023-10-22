package com.example.jumpparkchallenger.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jumpparkchallenger.domain.usecases.GetUser
import com.example.jumpparkchallenger.domain.usecases.Logout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val logoutUseCase: Logout, private val getUserUseCase : GetUser) : ViewModel() {

    val responseLogout = MutableLiveData<Boolean>()
    val responseUserEmail = MutableLiveData<String>()

    fun logout(){
        viewModelScope.launch(Dispatchers.IO){
            val result = logoutUseCase()
            responseLogout.postValue(result)
        }
    }

    fun getUser(){
        viewModelScope.launch(Dispatchers.IO){
            val result = getUserUseCase()
            responseUserEmail.postValue(result)
        }
    }
}