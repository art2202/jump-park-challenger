package com.example.jumpparkchallenger.domain.repository

import com.example.jumpparkchallenger.domain.entities.Vehicle

interface CheckOutRepository {

    fun calculateValue(vehicle: Vehicle) : Pair<Int, Double>
}