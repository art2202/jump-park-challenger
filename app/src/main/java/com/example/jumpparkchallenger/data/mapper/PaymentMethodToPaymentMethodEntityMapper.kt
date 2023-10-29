package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.database.entity.PaymentMethodEntity
import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod

class PaymentMethodToPaymentMethodEntityMapper : Mapper<PaymentMethod, PaymentMethodEntity> {
    override fun map(input: PaymentMethod): PaymentMethodEntity {
        return PaymentMethodEntity(
            id = input.id,
            name = input.name,
            total = input.total
        )
    }
}
