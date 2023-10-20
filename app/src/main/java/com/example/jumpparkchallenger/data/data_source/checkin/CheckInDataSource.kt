package com.example.jumpparkchallenger.data.data_source.checkin

import com.example.jumpparkchallenger.data.database.entity.PriceEntity
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import com.example.jumpparkchallenger.data.database.entity.VehicleEntity

interface CheckInDataSource {

    suspend fun getPrices() : List<PriceEntity>

    suspend fun getValueDetail(priceType : String) : List<ValueDetailEntity>

    suspend fun saveVehicle(vehicleEntity: VehicleEntity): Boolean
}