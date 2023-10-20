package com.example.jumpparkchallenger.data.data_source.vehicle_list

import com.example.jumpparkchallenger.data.database.entity.PriceEntity
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import com.example.jumpparkchallenger.data.database.entity.VehicleEntity

interface VehicleListDataSource {

    suspend fun getAllVehicles() : List<VehicleEntity>

    suspend fun getPrice(priceType : String) : PriceEntity?

    suspend fun getValueDetail(priceType : String) : List<ValueDetailEntity>
}