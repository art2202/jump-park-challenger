package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.database.entity.PaymentMethodEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class PaymentMethodEntityToPaymentMethodMapperTest {

    private val mapper = PaymentMethodEntityToPaymentMethodMapper()

    @Test
    fun `test map function maps PaymentMethodEntity to PaymentMethod correctly`() {
        val testPaymentMethodEntity = PaymentMethodEntity(
            id = 1,
            name = "debito",
            total = 100.50
        )

        val result = mapper.map(testPaymentMethodEntity)

        assertEquals(1, result.id)
        assertEquals("debito", result.name)
        assertEquals(100.50, result.total, 0.001)
    }
}
