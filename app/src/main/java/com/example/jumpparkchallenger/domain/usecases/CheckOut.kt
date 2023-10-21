package com.example.jumpparkchallenger.domain.usecases

import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import com.example.jumpparkchallenger.domain.repository.CheckOutRepository

class CheckOut(private val checkOutRepository: CheckOutRepository) {

    suspend operator fun invoke(vehicle: Vehicle, paymentSelected: PaymentMethod){
        checkOutRepository.checkOut(vehicle, paymentSelected)
    }
}