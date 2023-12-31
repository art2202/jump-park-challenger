package com.example.jumpparkchallenger.data.models.login

import com.google.gson.annotations.SerializedName

data class SessionDataResponse(
    @SerializedName("sessionId")
    val id: Int?,
    val establishmentId: Int?
)