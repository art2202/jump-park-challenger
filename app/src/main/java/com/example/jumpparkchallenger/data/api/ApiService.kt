package com.example.jumpparkchallenger.data.api

import com.example.jumpparkchallenger.data.models.home.HomeResponseData
import com.example.jumpparkchallenger.data.models.login.ResponseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("user/login")
    suspend fun login(@Body body : Map<String, String>) : Response<ResponseData>

    @GET("{userId}/establishment/{establishmentId}/sync/manual")
    suspend fun manualLoad(
        @Path("userId") userId: Int,
        @Path("establishmentId") establishmentId: Int,
        @Header("Authorization") token : String
    ) : Response<HomeResponseData>

    @POST("{userId}/establishment/{establishmentId}/session/close/{sessionId}")
    suspend fun logout(
        @Path("userId") userId: Int,
        @Path("establishmentId") establishmentId: Int,
        @Path("sessionId") sessionId : Int,
        @Header("Authorization") token : String
    ) : Response<Any>
}