package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.database.entity.SessionEntity
import com.example.jumpparkchallenger.data.models.login.SessionDataResponse

class SessionDataResponseToSessionEntityMapper : Mapper<SessionDataResponse, SessionEntity> {
    override fun map(input: SessionDataResponse): SessionEntity {
        return SessionEntity(
            id = input.id,
            establishmentId = input.establishmentId
        )
    }
}


