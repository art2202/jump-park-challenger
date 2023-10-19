package com.example.jumpparkchallenger.data.repository

import com.example.jumpparkchallenger.data.data_source.home.HomeDataSource
import com.example.jumpparkchallenger.data.data_source.home.HomeLocalDataSource
import com.example.jumpparkchallenger.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val homeDataSource: HomeDataSource,
    private val homeLocalDataSource: HomeLocalDataSource
) : HomeRepository {
    override suspend fun getData() {

        val userEntity = homeLocalDataSource.getUser()
        val establishmentEntity = homeLocalDataSource.getEstablishment()
        val data = homeDataSource.getData(userEntity?.id ?: 0, establishmentEntity?.id ?: 0)

    }
}