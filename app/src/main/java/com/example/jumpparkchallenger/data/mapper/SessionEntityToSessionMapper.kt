package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.database.entity.SessionEntity
import com.example.jumpparkchallenger.domain.entities.Session

class SessionEntityToSessionMapper : Mapper<SessionEntity, Session> {
    override fun map(input: SessionEntity): Session {
        return Session(
            id = input.id,
            establishmentId = input.establishmentId
        )
    }
}
