package com.example.jumpparkchallenger.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jumpparkchallenger.domain.entities.HomeInfos
import com.example.jumpparkchallenger.domain.usecases.CheckToken
import com.example.jumpparkchallenger.domain.usecases.Login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: Login, private val checkTokenUseCase: CheckToken) : ViewModel() {

    val responseHome = MutableLiveData<HomeInfos?>()
    val errorMessage = MutableLiveData<String?>()
    val tokenExists = MutableLiveData<Boolean>()

    fun login(email : String, password : String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = loginUseCase(email, password)
                responseHome.postValue(result)
            }
            catch (t : Throwable){
                errorMessage.postValue(t.message)
            }
        }
    }

    fun checkToken(){
        viewModelScope.launch(Dispatchers.IO){
            val result = checkTokenUseCase()
            tokenExists.postValue(result)
        }
    }
}