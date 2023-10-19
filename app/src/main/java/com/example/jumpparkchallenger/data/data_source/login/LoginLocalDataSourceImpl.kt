package com.example.jumpparkchallenger.data.data_source.login

import com.example.jumpparkchallenger.core.utils.SharedPreferenceHelper
import com.example.jumpparkchallenger.data.database.AppDatabase
import com.example.jumpparkchallenger.data.database.dao.EstablishmentDao
import com.example.jumpparkchallenger.data.database.dao.UserDao
import com.example.jumpparkchallenger.data.database.entity.EstablishmentEntity
import com.example.jumpparkchallenger.data.database.entity.UserEntity

class LoginLocalDataSourceImpl(
    private val sharedPreferenceHelper: SharedPreferenceHelper,
    private val userDao: UserDao,
    private val establishmentDao: EstablishmentDao
) :
    LoginLocalDataSource {

    override suspend fun saveData(
        userEntity: UserEntity,
        establishmentEntity: EstablishmentEntity,
        token: String
    ) {
        saveUser(userEntity)
        saveEstablishment(establishmentEntity)
        saveToken(token)
    }

    private suspend fun saveUser(userEntity: UserEntity) = userDao.insertUser(userEntity)

    private suspend fun saveEstablishment(establishmentEntity: EstablishmentEntity) {
        establishmentDao.insertEstablishment(establishmentEntity)
    }

    private fun saveToken(token : String?) {
        if(token != null)
            sharedPreferenceHelper.saveToken(token)
        else
            throw Throwable("Não foi possivel pegar o token")
    }

    override suspend fun getLocalToken() = sharedPreferenceHelper.getToken()
}