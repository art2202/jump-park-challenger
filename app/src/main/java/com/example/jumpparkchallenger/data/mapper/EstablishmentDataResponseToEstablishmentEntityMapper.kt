package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.database.entity.EstablishmentEntity
import com.example.jumpparkchallenger.data.models.EstablishmentDataResponse


class EstablishmentDataResponseToEstablishmentEntityMapper : Mapper<EstablishmentDataResponse, EstablishmentEntity> {

    override fun map(input: EstablishmentDataResponse): EstablishmentEntity {
        return EstablishmentEntity(
            id = input.id,
            name = input.name,
            vacanciesMarks = input.vacanciesMarks
        )
    }
}
