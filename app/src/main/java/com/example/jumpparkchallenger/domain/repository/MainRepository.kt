package com.example.jumpparkchallenger.domain.repository

interface MainRepository {

    suspend fun logout() : Boolean
}