package com.example.jumpparkchallenger.domain.usecases

import com.example.jumpparkchallenger.domain.entities.home.Price
import com.example.jumpparkchallenger.domain.repository.CheckInRepository

class GetPrices(private val checkInRepository : CheckInRepository) {

    suspend operator fun invoke() : List<Price> = checkInRepository.getPrices()
}