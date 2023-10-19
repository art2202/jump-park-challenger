package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.database.entity.PriceEntity
import com.example.jumpparkchallenger.data.models.home.PricesResponseData

class PricesResponseToPriceEntityMapper : Mapper<PricesResponseData, PriceEntity> {
    override fun map(input: PricesResponseData): PriceEntity {
        return PriceEntity(
            priceType = input.type ?: "",
            tolerance = input.tolerance,
            maximumPeriod = input.maximumPeriod,
            maximumValue = input.maximumValue
        )
    }
}