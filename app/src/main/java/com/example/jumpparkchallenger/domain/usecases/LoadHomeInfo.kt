package com.example.jumpparkchallenger.domain.usecases

import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import com.example.jumpparkchallenger.domain.entities.home.Price
import com.example.jumpparkchallenger.domain.repository.HomeRepository

class LoadHomeInfo(private val homeRepository: HomeRepository) {

    suspend operator fun invoke() : Pair<List<Price>, List<PaymentMethod>> = homeRepository.getData()
}