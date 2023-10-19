package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.database.entity.PriceEntity
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import com.example.jumpparkchallenger.domain.entities.home.Price

class PriceEntityToPriceMapper(
    private val valueDetailEntities: List<ValueDetailEntity>,
    private val valueDetailEntityToDomainMapper: ValueDetailEntityToValueDetailMapper
) : Mapper<PriceEntity, Price> {
    override fun map(input: PriceEntity): Price {
        val valueDetails = valueDetailEntities.filter { it.tablePriceId == input.id }
            .map { valueDetailEntityToDomainMapper.map(it) }

        return Price(
            establishmentId = input.id,
            priceType = input.priceType,
            tolerance = input.tolerance,
            maximumPeriod = input.maximumPeriod,
            maximumValue = input.maximumValue,
            valueDetails = valueDetails
        )
    }
}