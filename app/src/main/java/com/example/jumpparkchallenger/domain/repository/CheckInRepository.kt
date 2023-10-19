package com.example.jumpparkchallenger.domain.repository

import com.example.jumpparkchallenger.domain.entities.home.Price

interface CheckInRepository {

    suspend fun getPrices() : List<Price>
}