package com.example.jumpparkchallenger.domain.entities

import com.example.jumpparkchallenger.domain.repository.CheckOutRepository

class CalculateValue(private val checkOutRepository: CheckOutRepository) {

    operator fun invoke(vehicle: Vehicle) : Pair<Int, Double> = checkOutRepository.calculateValue(vehicle)
}