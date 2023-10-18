package com.example.jumpparkchallenger.domain.repository

import com.example.jumpparkchallenger.domain.entities.Login

interface LoginRepository {

    suspend fun login(email : String, password : String) : Login
}