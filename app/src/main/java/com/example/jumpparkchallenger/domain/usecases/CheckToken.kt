package com.example.jumpparkchallenger.domain.usecases

import com.example.jumpparkchallenger.domain.repository.LoginRepository

class CheckToken(private val loginRepository: LoginRepository) {
    suspend operator fun invoke() : Boolean = loginRepository.checkToken()
}