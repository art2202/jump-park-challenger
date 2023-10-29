package com.example.jumpparkchallenger.data.mapper
import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import org.junit.Assert.assertEquals
import org.junit.Test

class PaymentMethodToPaymentMethodEntityMapperTest {

    private val mapper = PaymentMethodToPaymentMethodEntityMapper()

    @Test
    fun `test map function maps PaymentMethod to PaymentMethodEntity correctly`() {
        val testPaymentMethod = PaymentMethod(
            id = 1,
            name = "debito",
            total = 12.50
        )

        val result = mapper.map(testPaymentMethod)

        assertEquals(1, result.id)
        assertEquals("debito", result.name)
        assertEquals(12.50, result.total, 0.001)
    }
}
