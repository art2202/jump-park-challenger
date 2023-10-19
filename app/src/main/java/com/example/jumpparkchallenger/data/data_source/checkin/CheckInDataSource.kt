package com.example.jumpparkchallenger.data.data_source.checkin

import com.example.jumpparkchallenger.data.database.entity.PriceEntity
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity

interface CheckInDataSource {

    suspend fun getPrices() : List<PriceEntity>

    suspend fun getValueDetail(priceType : String) : List<ValueDetailEntity>
}