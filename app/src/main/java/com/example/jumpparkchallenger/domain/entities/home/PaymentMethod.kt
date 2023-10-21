package com.example.jumpparkchallenger.domain.entities.home

data class PaymentMethod(
    val id: Int,
    val name: String,
    var total : Double
)