package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import com.example.jumpparkchallenger.data.models.home.ValueResponseData

class ValueResponseToValueEntityMapper :
    Mapper<ValueResponseData, ValueDetailEntity> {
    override fun map(input: ValueResponseData): ValueDetailEntity {
        return ValueDetailEntity(
            price = input.price,
            period = input.period,
            priceType = input.type,
        )
    }
}