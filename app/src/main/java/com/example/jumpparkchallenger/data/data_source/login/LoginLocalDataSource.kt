package com.example.jumpparkchallenger.data.data_source.login

import com.example.jumpparkchallenger.data.database.entity.EstablishmentEntity
import com.example.jumpparkchallenger.data.database.entity.UserEntity

interface LoginLocalDataSource {

    suspend fun saveData(userEntity: UserEntity, establishmentEntity: EstablishmentEntity, token : String)

    suspend fun getLocalToken() : String
}