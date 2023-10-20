package com.example.jumpparkchallenger.domain.repository

import com.example.jumpparkchallenger.domain.entities.Vehicle

interface VehicleListRepository {

    suspend fun getAllVehicles() : List<Vehicle>
}