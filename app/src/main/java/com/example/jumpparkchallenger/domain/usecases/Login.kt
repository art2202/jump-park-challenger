package com.example.jumpparkchallenger.domain.usecases

import com.example.jumpparkchallenger.domain.entities.LoginInfos
import com.example.jumpparkchallenger.domain.repository.LoginRepository

class Login(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(email : String, password : String) : LoginInfos = loginRepository.login(email, password)
}