package com.example.jumpparkchallenger.data.data_source

import com.example.jumpparkchallenger.data.models.LoginDataResponse

interface LoginDataSource {

    suspend fun login(email : String, password : String) : LoginDataResponse
    fun getLocalToken() : String

}