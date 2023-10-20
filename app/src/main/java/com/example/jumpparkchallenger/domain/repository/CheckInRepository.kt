package com.example.jumpparkchallenger.domain.repository

import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.entities.home.Price

interface CheckInRepository {

    suspend fun getPrices() : List<Price>
    suspend fun saveVehicle(vehicle: Vehicle): Boolean
}