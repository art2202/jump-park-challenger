package com.example.jumpparkchallenger.data.mapper
import com.example.jumpparkchallenger.data.models.home.PricesResponseData
import org.junit.Assert.assertEquals
import org.junit.Test

class PricesResponseToPriceEntityMapperTest {

    private val mapper = PricesResponseToPriceEntityMapper()

    @Test
    fun `test map function maps PricesResponseData to PriceEntity correctly`() {
        val testResponseData = PricesResponseData(
            type = "diario",
            tolerance = 10,
            maximumPeriod = 900,
            maximumValue = "2000.0",
            values = emptyList()
        )

        val result = mapper.map(testResponseData)

        assertEquals("diario", result.priceType)
        assertEquals(10, result.tolerance)
        assertEquals(900, result.maximumPeriod)
        assertEquals("2000.0", result.maximumValue)
    }
}
