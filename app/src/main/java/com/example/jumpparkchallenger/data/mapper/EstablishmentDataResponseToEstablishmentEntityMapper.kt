package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.database.entity.EstablishmentEntity
import com.example.jumpparkchallenger.data.models.login.EstablishmentDataResponse


class EstablishmentDataResponseToEstablishmentEntityMapper : Mapper<EstablishmentDataResponse, EstablishmentEntity> {

    override fun map(input: EstablishmentDataResponse): EstablishmentEntity {
        return EstablishmentEntity(
            id = input.id ?: 0,
            name = input.name,
            vacanciesMarks = input.vacanciesMarks
        )
    }
}
