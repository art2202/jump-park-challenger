package com.example.jumpparkchallenger.data.data_source.login

import com.example.jumpparkchallenger.core.utils.SharedPreferenceHelper
import com.example.jumpparkchallenger.data.database.AppDatabase
import com.example.jumpparkchallenger.data.database.dao.EstablishmentDao
import com.example.jumpparkchallenger.data.database.dao.SessionDao
import com.example.jumpparkchallenger.data.database.dao.UserDao
import com.example.jumpparkchallenger.data.database.entity.EstablishmentEntity
import com.example.jumpparkchallenger.data.database.entity.SessionEntity
import com.example.jumpparkchallenger.data.database.entity.UserEntity

class LoginLocalDataSourceImpl(
    private val sharedPreferenceHelper: SharedPreferenceHelper,
    private val userDao: UserDao,
    private val establishmentDao: EstablishmentDao,
    private val sessionDao: SessionDao
) :
    LoginLocalDataSource {

    override suspend fun saveData(
        userEntity: UserEntity,
        establishmentEntity: EstablishmentEntity,
        sessionEntity: SessionEntity,
        token: String
    ) {
        saveUser(userEntity)
        saveEstablishment(establishmentEntity)
        saveSession(sessionEntity)
        saveToken(token)
    }

    private fun saveUser(userEntity: UserEntity) = userDao.insertUser(userEntity)

    private fun saveEstablishment(establishmentEntity: EstablishmentEntity) {
        establishmentDao.insertEstablishment(establishmentEntity)
    }

    private fun saveSession(sessionEntity: SessionEntity){
        sessionDao.insertSession(sessionEntity)
    }

    private fun saveToken(token : String?) {
        if(token != null)
            sharedPreferenceHelper.saveToken(token)
        else
            throw Throwable("Não foi possivel pegar o token")
    }

    override suspend fun getLocalToken() = sharedPreferenceHelper.getToken()
}