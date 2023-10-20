package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.database.entity.PriceEntity
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import com.example.jumpparkchallenger.data.database.entity.VehicleEntity
import com.example.jumpparkchallenger.domain.entities.Vehicle

class VehicleEntityToVehicleMapper(private val priceEntityToPriceMapper: PriceEntityToPriceMapper)
    : Mapper<Triple<VehicleEntity, PriceEntity, List<ValueDetailEntity>>, Vehicle> {
    override fun map(input: Triple<VehicleEntity, PriceEntity, List<ValueDetailEntity>>): Vehicle {
        val price = priceEntityToPriceMapper.map(Pair(input.second, input.third))
        return Vehicle(
            plate = input.first.plate,
            model = input.first.model,
            color = input.first.color,
            price = price
        )
    }
}