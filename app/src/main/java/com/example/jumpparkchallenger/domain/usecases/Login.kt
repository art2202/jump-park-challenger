package com.example.jumpparkchallenger.domain.usecases

import com.example.jumpparkchallenger.domain.entities.HomeInfos
import com.example.jumpparkchallenger.domain.repository.LoginRepository

class Login(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(email : String, password : String) : HomeInfos = loginRepository.login(email, password)
}