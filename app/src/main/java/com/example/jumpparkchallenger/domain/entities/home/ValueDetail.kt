package com.example.jumpparkchallenger.domain.entities.home

import java.io.Serializable

data class ValueDetail(
    val price: String?,
    val period: Int?,
    val priceType: String?
) : Serializable