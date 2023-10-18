package com.example.jumpparkchallenger.data.api

import com.example.jumpparkchallenger.data.models.LoginDataResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("user/login")
    suspend fun login(@Body body : Map<String, String>) : Response<LoginDataResponse>
}