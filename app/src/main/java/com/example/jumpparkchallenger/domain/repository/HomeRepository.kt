package com.example.jumpparkchallenger.domain.repository

import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import com.example.jumpparkchallenger.domain.entities.home.Price

interface HomeRepository {

    suspend fun getData(): Pair<Int, List<PaymentMethod>>
}