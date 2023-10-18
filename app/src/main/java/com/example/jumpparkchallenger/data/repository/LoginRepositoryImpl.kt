package com.example.jumpparkchallenger.data.repository

import com.example.jumpparkchallenger.data.data_source.LoginDataSource
import com.example.jumpparkchallenger.data.mapper.LoginDataResponseToHomeInfosMapper
import com.example.jumpparkchallenger.domain.entities.HomeInfos
import com.example.jumpparkchallenger.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val loginDataSource: LoginDataSource,
    private val loginMapper : LoginDataResponseToHomeInfosMapper
) : LoginRepository {
    override suspend fun login(email: String, password: String) : HomeInfos {
        try {
            return loginMapper.map(loginDataSource.login(email, password))
        } catch (e: Exception) {
            throw e
        }
    }
}