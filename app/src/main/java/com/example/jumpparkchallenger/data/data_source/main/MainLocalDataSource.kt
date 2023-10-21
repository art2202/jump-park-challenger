package com.example.jumpparkchallenger.data.data_source.main

import com.example.jumpparkchallenger.data.database.entity.EstablishmentEntity
import com.example.jumpparkchallenger.data.database.entity.SessionEntity
import com.example.jumpparkchallenger.data.database.entity.UserEntity

interface MainLocalDataSource {

    suspend fun getUser( ): UserEntity?

    suspend fun getEstablishment() : EstablishmentEntity?

    suspend fun getSession() : SessionEntity?

    suspend fun clearDatabase()

}