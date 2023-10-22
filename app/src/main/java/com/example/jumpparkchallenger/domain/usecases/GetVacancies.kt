package com.example.jumpparkchallenger.domain.usecases

import com.example.jumpparkchallenger.domain.repository.MainRepository

class GetVacancies(private val mainRepository: MainRepository) {

    suspend operator fun invoke() : Int = mainRepository.getVacancies()
}