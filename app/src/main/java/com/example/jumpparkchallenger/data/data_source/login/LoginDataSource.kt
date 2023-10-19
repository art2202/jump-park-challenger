package com.example.jumpparkchallenger.data.data_source.login

import com.example.jumpparkchallenger.data.models.login.LoginDataResponse

interface LoginDataSource {

    suspend fun login(email : String, password : String) : LoginDataResponse

}