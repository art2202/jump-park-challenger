package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.database.entity.PriceEntity
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import com.example.jumpparkchallenger.domain.entities.home.Price

class PriceEntityToPriceMapper(
    private val valueDetailEntityToDomainMapper: ValueDetailEntityToValueDetailMapper
) : Mapper<Pair<PriceEntity, List<ValueDetailEntity>>, Price> {

    override fun map(input: Pair<PriceEntity, List<ValueDetailEntity>>): Price {

        val valueDetails = input.second.filter { it.tablePriceId == input.first.id }
            .map { valueDetailEntityToDomainMapper.map(it) }

        return Price(
            establishmentId = input.first.id,
            priceType = input.first.priceType,
            tolerance = input.first.tolerance,
            maximumPeriod = input.first.maximumPeriod,
            maximumValue = input.first.maximumValue,
            valueDetails = valueDetails
        )
    }
}