package com.example.jumpparkchallenger.data.models.home

import com.google.gson.annotations.SerializedName

data class PricesResponseData(
    @SerializedName("establishmentId")
    val id : Int?,
    @SerializedName("typePrice")
    val type : String?,
    val tolerance : Int?,
    val maximumPeriod : Int?,
    val maximumValue : String?,
    @SerializedName("items")
    val values : List<ValueResponseData>
)