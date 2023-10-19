package com.example.jumpparkchallenger.data.data_source.home

import com.example.jumpparkchallenger.data.database.entity.EstablishmentEntity
import com.example.jumpparkchallenger.data.database.entity.UserEntity

interface HomeLocalDataSource {

    suspend fun getUser() : UserEntity?

    suspend fun getEstablishment() : EstablishmentEntity?
}