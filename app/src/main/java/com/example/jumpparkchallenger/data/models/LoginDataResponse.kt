package com.example.jumpparkchallenger.data.models

import com.google.gson.annotations.SerializedName

data class LoginDataResponse(
    @SerializedName("user")
    val userDataResponse: UserDataResponse?,
    @SerializedName("session")
    val sessionDataResponse: SessionDataResponse?,
    @SerializedName("establishments")
    val establishmentDataResponse: List<EstablishmentDataResponse?>
)