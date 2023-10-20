package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.database.entity.VehicleEntity
import com.example.jumpparkchallenger.domain.entities.Vehicle

class VehicleToVehicleEntityMapper : Mapper<Vehicle, VehicleEntity> {
    override fun map(input: Vehicle): VehicleEntity {
        return VehicleEntity(
            input.plate,
            input.model,
            input.color,
            input.price.priceType ?: "",
            input.date
        )
    }
}