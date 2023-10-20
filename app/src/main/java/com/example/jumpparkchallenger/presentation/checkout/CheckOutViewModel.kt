package com.example.jumpparkchallenger.presentation.checkout

import androidx.lifecycle.ViewModel
import com.example.jumpparkchallenger.domain.entities.CalculateValue
import com.example.jumpparkchallenger.domain.entities.Vehicle

class CheckOutViewModel(private val calculateValueUseCase: CalculateValue) : ViewModel() {

    fun calculateValue(vehicle: Vehicle): Pair<Int, Double> {
        return calculateValueUseCase(vehicle)
    }
}