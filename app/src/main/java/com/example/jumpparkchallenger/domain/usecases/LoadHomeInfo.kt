package com.example.jumpparkchallenger.domain.usecases

import com.example.jumpparkchallenger.domain.repository.HomeRepository

class LoadHomeInfo(private val homeRepository: HomeRepository) {

    suspend operator fun invoke() = homeRepository.getData()
}