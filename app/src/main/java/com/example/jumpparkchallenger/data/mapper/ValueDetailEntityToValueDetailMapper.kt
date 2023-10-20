package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import com.example.jumpparkchallenger.domain.entities.home.ValueDetail

class ValueDetailEntityToValueDetailMapper : Mapper<ValueDetailEntity, ValueDetail> {
    override fun map(input: ValueDetailEntity): ValueDetail {
        return ValueDetail(
            price = input.price,
            period = input.period,
            since = input.since,
            priceType = input.priceType
        )
    }
}