package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.database.entity.PaymentMethodEntity
import com.example.jumpparkchallenger.data.models.home.PaymentMethodosResponseData

class PaymentMethodResponseToPaymentMethodEntityMapper: Mapper<PaymentMethodosResponseData, PaymentMethodEntity> {
    override fun map(input: PaymentMethodosResponseData): PaymentMethodEntity {
        return PaymentMethodEntity(
            id = input.id,
            name = input.name
        )
    }
}