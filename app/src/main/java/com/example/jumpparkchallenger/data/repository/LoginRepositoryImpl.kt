package com.example.jumpparkchallenger.data.repository

import com.example.jumpparkchallenger.data.data_source.login.LoginDataSource
import com.example.jumpparkchallenger.data.data_source.login.LoginLocalDataSource
import com.example.jumpparkchallenger.data.mapper.EstablishmentDataResponseToEstablishmentEntityMapper
import com.example.jumpparkchallenger.data.mapper.LoginDataResponseToHomeInfosMapper
import com.example.jumpparkchallenger.data.mapper.SessionDataResponseToSessionEntityMapper
import com.example.jumpparkchallenger.data.mapper.UserDataResponseToUserEntityMapper
import com.example.jumpparkchallenger.data.models.login.LoginDataResponse
import com.example.jumpparkchallenger.domain.entities.LoginInfos
import com.example.jumpparkchallenger.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val loginDataSource: LoginDataSource,
    private val loginLocalDataSource: LoginLocalDataSource,
    private val loginMapper : LoginDataResponseToHomeInfosMapper,
    private val userDataResponseToUserEntityMapper: UserDataResponseToUserEntityMapper,
    private val sessionDataResponseToSessionEntityMapper: SessionDataResponseToSessionEntityMapper,
    private val establishmentDataResponseToEstablishmentEntityMapper: EstablishmentDataResponseToEstablishmentEntityMapper
) : LoginRepository {
    override suspend fun login(email: String, password: String) : LoginInfos {
        try {
            val data = loginDataSource.login(email, password)
            saveLocal(data)
            return loginMapper.map(data)
        } catch (e: Exception) {
            throw e
        }
    }

    private suspend fun saveLocal(data : LoginDataResponse) {
        val userEntity = userDataResponseToUserEntityMapper.map(data.userDataResponse!!)
        val sessionEntity = sessionDataResponseToSessionEntityMapper.map(data.sessionDataResponse!!)
        val establishmentEntity = establishmentDataResponseToEstablishmentEntityMapper.map(data.establishmentDataResponse[0]!!)
        loginLocalDataSource.saveData(userEntity, establishmentEntity, sessionEntity,data.userDataResponse.accessToken ?: "")
    }

    override suspend fun checkToken(): Boolean {
        val token = loginLocalDataSource.getLocalToken()
        return token.isNotEmpty()
    }
}