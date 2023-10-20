package com.example.jumpparkchallenger.data.repository

import com.example.jumpparkchallenger.data.data_source.checkin.CheckInDataSource
import com.example.jumpparkchallenger.data.mapper.PriceEntityToPriceMapper
import com.example.jumpparkchallenger.data.mapper.ValueDetailEntityToValueDetailMapper
import com.example.jumpparkchallenger.data.mapper.VehicleToVehicleEntityMapper
import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.entities.home.Price
import com.example.jumpparkchallenger.domain.repository.CheckInRepository

class CheckInRepositoryImpl(
    private val checkInDataSource: CheckInDataSource,
    private val priceEntityToPriceMapper: PriceEntityToPriceMapper,
    private val vehicleToVehicleEntityMapper: VehicleToVehicleEntityMapper
) : CheckInRepository {
    override suspend fun getPrices(): List<Price> {
        val priceEntities = checkInDataSource.getPrices()

        return priceEntities.map {
            val valueDetailEntities = checkInDataSource.getValueDetail(it.priceType)
            priceEntityToPriceMapper.map(Pair(it, valueDetailEntities))
        }
    }

    override suspend fun saveVehicle(vehicle: Vehicle): Boolean {
        val vehicleEntity = vehicleToVehicleEntityMapper.map(vehicle)
        return checkInDataSource.saveVehicle(vehicleEntity)
    }
}