package com.example.jumpparkchallenger.domain.repository

import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod

interface CheckOutRepository {

    suspend fun getPaymentsMethod() : List<PaymentMethod>

    fun calculateValue(vehicle: Vehicle) : Pair<Int, Double>
}