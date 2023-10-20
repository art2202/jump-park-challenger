package com.example.jumpparkchallenger.data.data_source.vehicle_list

import com.example.jumpparkchallenger.data.database.dao.PriceDao
import com.example.jumpparkchallenger.data.database.dao.ValueDetailDao
import com.example.jumpparkchallenger.data.database.dao.VehicleDao
import com.example.jumpparkchallenger.data.database.entity.PriceEntity
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import com.example.jumpparkchallenger.data.database.entity.VehicleEntity

class VehicleListDataSourceImpl(
    private val vehicleDao: VehicleDao,
    private val priceDao: PriceDao,
    private val valueDetailDao: ValueDetailDao
) : VehicleListDataSource {
    override suspend fun getAllVehicles(): List<VehicleEntity> {
        return vehicleDao.getAllVehicles()
    }

    override suspend fun getPrice(priceType: String): PriceEntity? {
        return priceDao.getPriceByPriceType(priceType)
    }

    override suspend fun getValueDetail(priceType: String): List<ValueDetailEntity> {
        return valueDetailDao.getValueDetailsByPriceId(priceType)
    }
}