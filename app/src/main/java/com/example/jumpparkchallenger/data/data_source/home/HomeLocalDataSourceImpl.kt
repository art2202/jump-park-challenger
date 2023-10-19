package com.example.jumpparkchallenger.data.data_source.home

import com.example.jumpparkchallenger.data.database.AppDatabase
import com.example.jumpparkchallenger.data.database.dao.EstablishmentDao
import com.example.jumpparkchallenger.data.database.dao.UserDao
import com.example.jumpparkchallenger.data.database.entity.EstablishmentEntity
import com.example.jumpparkchallenger.data.database.entity.UserEntity

class HomeLocalDataSourceImpl(private val userDao: UserDao,
                              private val establishmentDao: EstablishmentDao
) : HomeLocalDataSource {

    override suspend fun getUser(): UserEntity? = userDao.getUser()

    override suspend fun getEstablishment(): EstablishmentEntity? = establishmentDao.getEstablishment()
}