package com.example.jumpparkchallenger.data.repository

import com.example.jumpparkchallenger.data.data_source.LoginDataSource
import com.example.jumpparkchallenger.data.mapper.LoginDataResponseToLoginMapper
import com.example.jumpparkchallenger.domain.entities.Login
import com.example.jumpparkchallenger.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val loginDataSource: LoginDataSource,
    private val loginMapper : LoginDataResponseToLoginMapper
) : LoginRepository {
    override suspend fun login(email: String, password: String) : Login {
        try {
            return loginMapper.map(loginDataSource.login(email, password))
        } catch (e: Exception) {
            throw e
        }
    }
}