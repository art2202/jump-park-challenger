package com.example.jumpparkchallenger.domain.usecases

import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.repository.VehicleListRepository

class VehicleList(private val vehicleListRepository: VehicleListRepository) {

    suspend operator fun invoke() : List<Vehicle> = vehicleListRepository.getAllVehicles()
}