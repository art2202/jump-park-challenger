package com.example.jumpparkchallenger.data.data_source.main

interface MainDataSource {

    suspend fun logout(userId : Int, establishmentId: Int, sessionId : Int) : Boolean
}