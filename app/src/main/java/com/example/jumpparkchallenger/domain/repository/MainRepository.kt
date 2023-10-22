package com.example.jumpparkchallenger.domain.repository


interface MainRepository {

    suspend fun logout() : Boolean

    suspend fun getUser() : String

    suspend fun getVacancies() : Int
}