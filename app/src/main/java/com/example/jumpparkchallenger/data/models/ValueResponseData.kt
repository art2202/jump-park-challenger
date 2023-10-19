package com.example.jumpparkchallenger.data.models

import com.google.gson.annotations.SerializedName

data class ValueResponseData(
    val price : String?,
    val period : Int?,
    @SerializedName("typePrice")
    val type : String?
)
