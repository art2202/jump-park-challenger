package com.example.jumpparkchallenger.data.data_source.checkin

import com.example.jumpparkchallenger.data.database.dao.PriceDao
import com.example.jumpparkchallenger.data.database.dao.ValueDetailDao
import com.example.jumpparkchallenger.data.database.entity.PriceEntity
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity

class CheckInDataSourceImpl(
    private val priceDao: PriceDao,
    private val valueDetailDao: ValueDetailDao
) : CheckInDataSource {
    override suspend fun getPrices(): List<PriceEntity> {
        return priceDao.getAllPrices()
    }

    override suspend fun getValueDetail(priceType : String): List<ValueDetailEntity> {
        return valueDetailDao.getValueDetailsByPriceId(priceType)
    }
}