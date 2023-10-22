package com.example.jumpparkchallenger.domain.usecases

import com.example.jumpparkchallenger.domain.repository.MainRepository

class GetUser(private val mainRepository: MainRepository) {

    suspend operator fun invoke() : String = mainRepository.getUser()

}