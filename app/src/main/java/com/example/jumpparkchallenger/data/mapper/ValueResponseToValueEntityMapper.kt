package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import com.example.jumpparkchallenger.data.models.home.ValueResponseData

class ValueResponseToValueEntityMapper :
    Mapper<Pair<Int, ValueResponseData>, ValueDetailEntity> {
    override fun map(input: Pair<Int, ValueResponseData>): ValueDetailEntity {
        return ValueDetailEntity(
            price = input.second.price,
            period = input.second.period,
            priceType = input.second.type,
            tablePriceId = input.first
        )
    }
}