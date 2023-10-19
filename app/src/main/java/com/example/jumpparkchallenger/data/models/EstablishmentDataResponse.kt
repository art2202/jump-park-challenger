package com.example.jumpparkchallenger.data.models

import com.google.gson.annotations.SerializedName

class EstablishmentDataResponse(
    @SerializedName("establishmentId")
    val id: Int?,
    val name: String?,
    val vacanciesMarks: Int?
)