package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.database.entity.PaymentMethodEntity
import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod

class PaymentMethodEntityToPaymentMethodMapper : Mapper<PaymentMethodEntity, PaymentMethod> {
    override fun map(input: PaymentMethodEntity): PaymentMethod {
        return PaymentMethod(
            id = input.id,
            name = input.name
        )
    }
}