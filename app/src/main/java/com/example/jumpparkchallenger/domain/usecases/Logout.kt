package com.example.jumpparkchallenger.domain.usecases

import com.example.jumpparkchallenger.domain.repository.MainRepository

class Logout(private val mainRepository: MainRepository) {

    suspend operator fun invoke() : Boolean = mainRepository.logout()
}