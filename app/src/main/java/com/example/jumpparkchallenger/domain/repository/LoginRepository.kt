package com.example.jumpparkchallenger.domain.repository

import com.example.jumpparkchallenger.domain.entities.LoginInfos

interface LoginRepository {

    suspend fun login(email : String, password : String) : LoginInfos
    suspend fun checkToken(): Boolean
}