package com.example.jumpparkchallenger.data.mapper
import com.example.jumpparkchallenger.data.database.entity.PriceEntity
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import com.example.jumpparkchallenger.data.database.entity.VehicleEntity
import com.example.jumpparkchallenger.domain.entities.home.Price
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.Date

class VehicleEntityToVehicleMapperTest {

    private lateinit var priceEntityToPriceMapper: PriceEntityToPriceMapper
    private lateinit var mapper: VehicleEntityToVehicleMapper

    @Before
    fun setUp() {
        priceEntityToPriceMapper = mockk(relaxed = true)
        mapper = VehicleEntityToVehicleMapper(priceEntityToPriceMapper)
    }

    @Test
    fun `test map function maps Triple to Vehicle correctly`() {

        val mockVehicleEntity = VehicleEntity(
            plate = "ABC1234",
            model = "CarModel",
            color = "Red",
            priceType = "diario",
            date = Date()
        )
        val mockPriceEntity = PriceEntity(
            priceType = "diario",
            tolerance = 10,
            maximumPeriod = 900,
            maximumValue = "500"
        )
        val mockValueDetailEntity = ValueDetailEntity(
            id = 1,
            price = "10",
            period = 60,
            since = 60,
            priceType = "rotativo"
        )
        val mockValueDetailEntityList = listOf(mockValueDetailEntity)
        val mockPrice = Price(
            priceType = mockPriceEntity.priceType,
            tolerance = mockPriceEntity.tolerance ?: 0,
            maximumPeriod = mockPriceEntity.maximumPeriod ?: 0,
            maximumValue = mockPriceEntity.maximumValue ?: "",
            valueDetails = listOf()
        )

        every { priceEntityToPriceMapper.map(Pair(mockPriceEntity, mockValueDetailEntityList)) } returns mockPrice

        val input = Triple(mockVehicleEntity, mockPriceEntity, mockValueDetailEntityList)

        val result = mapper.map(input)

        assertEquals(mockVehicleEntity.plate, result.plate)
        assertEquals(mockVehicleEntity.model, result.model)
        assertEquals(mockVehicleEntity.color, result.color)
        assertEquals(mockPrice, result.price)
        assertEquals(mockVehicleEntity.date, result.date)
    }
}


