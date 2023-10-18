package com.example.jumpparkchallenger.data.api

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("user/login")
    suspend fun makeLogin(@Body body : HashMap<String, String>)
}