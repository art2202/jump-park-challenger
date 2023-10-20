package com.example.jumpparkchallenger.domain.usecases

import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.repository.CheckInRepository

class SaveVehicle(private val checkInRepository: CheckInRepository) {

    suspend operator fun invoke(vehicle: Vehicle) : Boolean = checkInRepository.saveVehicle(vehicle)
}