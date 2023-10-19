package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.database.entity.UserEntity
import com.example.jumpparkchallenger.data.models.login.UserDataResponse

class UserDataResponseToUserEntityMapper : Mapper<UserDataResponse, UserEntity> {

    override fun map(input: UserDataResponse): UserEntity {
        return UserEntity(
            id = input.id ?: 0,
            name = input.name,
            email = input.email
        )
    }
}
