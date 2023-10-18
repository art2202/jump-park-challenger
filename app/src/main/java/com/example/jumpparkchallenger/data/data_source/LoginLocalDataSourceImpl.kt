package com.example.jumpparkchallenger.data.data_source

import com.example.jumpparkchallenger.core.utils.SharedPreferenceHelper
import com.example.jumpparkchallenger.data.database.AppDatabase
import com.example.jumpparkchallenger.data.database.entity.EstablishmentEntity
import com.example.jumpparkchallenger.data.database.entity.UserEntity
import com.example.jumpparkchallenger.data.models.EstablishmentDataResponse
import com.example.jumpparkchallenger.data.models.LoginDataResponse
import com.example.jumpparkchallenger.data.models.UserDataResponse

class LoginLocalDataSourceImpl(private val sharedPreferenceHelper: SharedPreferenceHelper) : LoginLocalDataSource {

    private val userDao = AppDatabase.getDatabase()!!.userDao()
    private val establishmentDao = AppDatabase.getDatabase()!!.establishmentDao()

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