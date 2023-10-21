package com.example.jumpparkchallenger.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jumpparkchallenger.domain.usecases.Logout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val logoutUseCase: Logout) : ViewModel() {

    val responseLogout = MutableLiveData<Boolean>()
    fun logout(){
        viewModelScope.launch(Dispatchers.IO){
            val result = logoutUseCase()
            responseLogout.postValue(result)
        }
    }
}