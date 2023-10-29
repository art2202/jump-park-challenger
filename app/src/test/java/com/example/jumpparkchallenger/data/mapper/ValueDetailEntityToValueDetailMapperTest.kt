package com.example.jumpparkchallenger.data.mapper
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class ValueDetailEntityToValueDetailMapperTest {

    private val mapper = ValueDetailEntityToValueDetailMapper()

    @Test
    fun `test map function maps ValueDetailEntity to ValueDetail correctly`() {
        val testEntity = ValueDetailEntity(
            id = 1,
            price = "10",
            period = 60,
            since = 10,
            priceType = "rotativo"
        )

        val result = mapper.map(testEntity)

        assertEquals("10", result.price)
        assertEquals(60, result.period)
        assertEquals(10, result.since)
        assertEquals("rotativo", result.priceType)
    }
}

