package com.example.jumpparkchallenger.data.repository
import com.example.jumpparkchallenger.data.data_source.checkin.CheckInDataSource
import com.example.jumpparkchallenger.data.database.entity.PriceEntity
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import com.example.jumpparkchallenger.data.database.entity.VehicleEntity
import com.example.jumpparkchallenger.data.mapper.PriceEntityToPriceMapper
import com.example.jumpparkchallenger.data.mapper.VehicleToVehicleEntityMapper
import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.entities.home.Price
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.Date

class CheckInRepositoryImplTest {

    private lateinit var repository: CheckInRepositoryImpl
    private lateinit var checkInDataSource: CheckInDataSource
    private lateinit var priceEntityToPriceMapper: PriceEntityToPriceMapper
    private lateinit var vehicleToVehicleEntityMapper: VehicleToVehicleEntityMapper

    @Before
    fun setUp() {
        checkInDataSource = mockk(relaxed = true)
        priceEntityToPriceMapper = mockk(relaxed = true)
        vehicleToVehicleEntityMapper = mockk(relaxed = true)
        repository = CheckInRepositoryImpl(checkInDataSource, priceEntityToPriceMapper, vehicleToVehicleEntityMapper)
    }

    @Test
    fun `test getPrices returns correct prices`() = runBlocking {
        val mockPriceEntity = PriceEntity(
            priceType = "Standard",
            tolerance = 10,
            maximumPeriod = 60,
            maximumValue = "100"
        )
        val mockValueDetailEntity = ValueDetailEntity(
            id = 1,
            price = "10",
            period = 60,
            since = 60,
            priceType = "rotativo"
        )
        val mockPrice = Price(
            priceType = "Standard",
            tolerance = 10,
            maximumPeriod = 60,
            maximumValue = "100",
            valueDetails = listOf()
        )

        coEvery { checkInDataSource.getPrices() } returns listOf(mockPriceEntity)
        coEvery { checkInDataSource.getValueDetail(mockPriceEntity.priceType) } returns listOf(mockValueDetailEntity)
        coEvery { priceEntityToPriceMapper.map(Pair(mockPriceEntity, listOf(mockValueDetailEntity))) } returns mockPrice

        val result = repository.getPrices()
        assertEquals(listOf(mockPrice), result)
    }

    @Test
    fun `test saveVehicle returns true when vehicle is saved successfully`() = runBlocking {
        val mockVehicle = Vehicle(
            plate = "ABC1234",
            model = "CarModel",
            color = "Red",
            price = Price(
                priceType = "Standard",
                tolerance = 10,
                maximumPeriod = 60,
                maximumValue = "100",
                valueDetails = listOf()
            ),
            date = Date()
        )
        val mockVehicleEntity = VehicleEntity(
            plate = "ABC1234",
            model = "CarModel",
            color = "Red",
            priceType = "Standard",
            date = Date()
        )

        coEvery { vehicleToVehicleEntityMapper.map(mockVehicle) } returns mockVehicleEntity
        coEvery { checkInDataSource.saveVehicle(mockVehicleEntity) } returns true

        val result = repository.saveVehicle(mockVehicle)
        assertEquals(true, result)
    }
}
