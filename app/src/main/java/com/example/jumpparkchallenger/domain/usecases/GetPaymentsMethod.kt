package com.example.jumpparkchallenger.domain.usecases

import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import com.example.jumpparkchallenger.domain.repository.CheckOutRepository

class GetPaymentsMethod(private val checkOutRepository: CheckOutRepository) {

    suspend operator fun invoke() : List<PaymentMethod> = checkOutRepository.getPaymentsMethod()

}