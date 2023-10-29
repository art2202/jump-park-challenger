package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.models.home.PaymentMethodosResponseData
import org.junit.Assert.assertEquals
import org.junit.Test

class PaymentMethodResponseToPaymentMethodEntityMapperTest {

    private val mapper = PaymentMethodResponseToPaymentMethodEntityMapper()

    @Test
    fun `test map function maps PaymentMethodosResponseData to PaymentMethodEntity correctly`() {
        val testPaymentMethodResponse = PaymentMethodosResponseData(
            id = 1,
            name = "debito"
        )

        val result = mapper.map(testPaymentMethodResponse)

        assertEquals(1, result.id)
        assertEquals("debito", result.name)
    }
}
