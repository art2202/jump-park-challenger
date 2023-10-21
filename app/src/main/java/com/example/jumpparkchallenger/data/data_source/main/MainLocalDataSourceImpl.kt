package com.example.jumpparkchallenger.data.data_source.main

import com.example.jumpparkchallenger.core.utils.SharedPreferenceHelper
import com.example.jumpparkchallenger.data.database.dao.EstablishmentDao
import com.example.jumpparkchallenger.data.database.dao.SessionDao
import com.example.jumpparkchallenger.data.database.dao.UserDao
import com.example.jumpparkchallenger.data.database.entity.EstablishmentEntity
import com.example.jumpparkchallenger.data.database.entity.SessionEntity
import com.example.jumpparkchallenger.data.database.entity.UserEntity

class MainLocalDataSourceImpl(
    private val userDao: UserDao,
    private val establishmentDao: EstablishmentDao,
    private val sessionDao: SessionDao,
    private val sharedPreferenceHelper: SharedPreferenceHelper
) : MainLocalDataSource {
    override suspend fun getUser(): UserEntity? {
        return userDao.getUser()
    }

    override suspend fun getEstablishment(): EstablishmentEntity? {
        return establishmentDao.getEstablishment()
    }

    override suspend fun getSession(): SessionEntity? {
        return sessionDao.getSession()
    }

    override suspend fun deleteUser() = userDao.deleteAllUsers()

    override suspend fun deleteEstablishment() = establishmentDao.deleteAllEstablishments()

    override suspend fun deleteSession() = sessionDao.deleteAllSessions()

    override suspend fun deleteToken() = sharedPreferenceHelper.clearMemory()

}