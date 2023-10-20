package com.example.jumpparkchallenger.data.repository

import com.example.jumpparkchallenger.data.data_source.vehicle_list.VehicleListDataSource
import com.example.jumpparkchallenger.data.mapper.VehicleEntityToVehicleMapper
import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.repository.VehicleListRepository

class VehicleListRepositoryImpl(
    private val vehicleListDataSource: VehicleListDataSource,
    private val vehicleEntityToVehicleMapper: VehicleEntityToVehicleMapper)
    : VehicleListRepository {
    override suspend fun getAllVehicles(): List<Vehicle> {

        val list = vehicleListDataSource.getAllVehicles()

        val prices = list.map {
            vehicleListDataSource.getPrice(it.priceType)
        }
        val valueDetails = prices.map { vehicleListDataSource.getValueDetail(it?.priceType ?: "") }

        val vehicles = list.mapNotNull { vehicleEntity ->

            val priceEntity = prices.find { it?.priceType == vehicleEntity.priceType }

            val valueDetailListForPrice = valueDetails.find {
                it.any {
                    detail -> detail.priceType == vehicleEntity.priceType
                }
            }

            if (priceEntity != null && valueDetailListForPrice != null) {
                vehicleEntityToVehicleMapper.map(Triple(vehicleEntity, priceEntity, valueDetailListForPrice))
            } else {
                null
            }
        }
        return vehicles
    }
}