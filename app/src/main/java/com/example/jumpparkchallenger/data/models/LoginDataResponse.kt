package com.example.jumpparkchallenger.data.models

data class LoginDataResponse(
    val userDataResponse: UserDataResponse?,
    val sessionDataResponse: SessionDataResponse?,
    val establishmentDataResponse: EstablishmentDataResponse?
)