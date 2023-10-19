package com.example.jumpparkchallenger.data.models.login

import com.google.gson.annotations.SerializedName

data class UserDataResponse(
    @SerializedName("userId")
    val id: Int?,
    val name: String?,
    val email: String?,
    val accessToken : String?
)