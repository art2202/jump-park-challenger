package com.example.jumpparkchallenger.domain.entities.home

data class Price(
    val priceType: String?,
    val tolerance: Int?,
    val maximumPeriod: Int?,
    val maximumValue: String?,
    val valueDetails: List<ValueDetail>
)