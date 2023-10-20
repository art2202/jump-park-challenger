package com.example.jumpparkchallenger.data.data_source.checkin

import com.example.jumpparkchallenger.data.database.dao.PriceDao
import com.example.jumpparkchallenger.data.database.dao.ValueDetailDao
import com.example.jumpparkchallenger.data.database.dao.VehicleDao
import com.example.jumpparkchallenger.data.database.entity.PriceEntity
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import com.example.jumpparkchallenger.data.database.entity.VehicleEntity

class CheckInDataSourceImpl(
    private val priceDao: PriceDao,
    private val valueDetailDao: ValueDetailDao,
    private val vehicleDao: VehicleDao
) : CheckInDataSource {
    override suspend fun getPrices(): List<PriceEntity> {
        return priceDao.getAllPrices()
    }

    override suspend fun getValueDetail(priceType : String): List<ValueDetailEntity> {
        return valueDetailDao.getValueDetailsByPriceId(priceType)
    }

    override suspend fun saveVehicle(vehicleEntity: VehicleEntity): Boolean {
        vehicleDao.insertVehicle(vehicleEntity)
        return true
    }
}