package com.example.jumpparkchallenger.data.repository
import com.example.jumpparkchallenger.data.data_source.checkout.CheckOutDataSource
import com.example.jumpparkchallenger.data.database.entity.PaymentMethodEntity
import com.example.jumpparkchallenger.data.database.entity.VehicleEntity
import com.example.jumpparkchallenger.data.mapper.PaymentMethodEntityToPaymentMethodMapper
import com.example.jumpparkchallenger.data.mapper.PaymentMethodToPaymentMethodEntityMapper
import com.example.jumpparkchallenger.data.mapper.VehicleToVehicleEntityMapper
import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import com.example.jumpparkchallenger.domain.entities.home.Price
import com.example.jumpparkchallenger.domain.entities.home.ValueDetail
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.Date

class CheckOutRepositoryImplTest {

    private lateinit var repository: CheckOutRepositoryImpl
    private val mockCheckOutDataSource: CheckOutDataSource = mockk(relaxed = true)
    private val mockPaymentMethodEntityToPaymentMethodMapper: PaymentMethodEntityToPaymentMethodMapper = mockk(relaxed = true)
    private val mockVehicleToVehicleEntityMapper: VehicleToVehicleEntityMapper = mockk(relaxed = true)
    private val mockPaymentMethodToPaymentMethodEntityMapper: PaymentMethodToPaymentMethodEntityMapper = mockk(relaxed = true)

    @Before
    fun setup() {
        repository = CheckOutRepositoryImpl(
            mockCheckOutDataSource,
            mockPaymentMethodEntityToPaymentMethodMapper,
            mockVehicleToVehicleEntityMapper,
            mockPaymentMethodToPaymentMethodEntityMapper
        )
    }

    @Test
    fun `checkOut should correctly map and call data source functions`() {
        val mockVehicle = mockk<Vehicle>(relaxed = true)
        val mockPaymentMethod = mockk<PaymentMethod>(relaxed = true)

        val mockVehicleEntity = mockk<VehicleEntity>(relaxed = true)
        val mockPaymentMethodEntity = mockk<PaymentMethodEntity>(relaxed = true)

        every { mockVehicleToVehicleEntityMapper.map(mockVehicle) } returns mockVehicleEntity
        every { mockPaymentMethodToPaymentMethodEntityMapper.map(mockPaymentMethod) } returns mockPaymentMethodEntity

        runBlocking {
            repository.checkOut(mockVehicle, mockPaymentMethod)
        }

        coVerify {
            mockCheckOutDataSource.deleteVehicle(mockVehicleEntity)
            mockCheckOutDataSource.savePayment(mockPaymentMethodEntity)
        }
    }

    @Test
    fun `getPaymentsMethod should return mapped payment methods`() {
        val mockPaymentMethodEntityList = listOf(mockk<PaymentMethodEntity>(relaxed = true))
        val mockPaymentMethod = mockk<PaymentMethod>(relaxed = true)

        coEvery { mockCheckOutDataSource.getPaymentsMethod() } returns mockPaymentMethodEntityList
        every { mockPaymentMethodEntityToPaymentMethodMapper.map(any()) } returns mockPaymentMethod

        runBlocking {
            val result = repository.getPaymentsMethod()
            assertEquals(listOf(mockPaymentMethod), result)
        }

    }

    @Test
    fun `calculateValue should return zero when time is within tolerance`() {
        val dateInPast = Date(System.currentTimeMillis() - 10 * 60 * 1000)  // 10 minutes ago
        val vehicle = Vehicle(
            plate = "ABC-1234",
            model = "Test Model",
            color = "Azul",
            price = Price(
                priceType = "Type1",
                tolerance = 15,
                maximumPeriod = 600,
                maximumValue = "100",
                valueDetails = emptyList()
            ),
            date = dateInPast
        )

        val result = repository.calculateValue(vehicle)

        assertEquals(Pair(10, 0.0), result)
    }

    @Test
    fun `calculateValue should return cost for a single period`() {
        val dateInPast = Date(System.currentTimeMillis() - 200 * 60 * 1000)
        val vehicle = Vehicle(
            plate = "ABC-1234",
            model = "Test Model",
            color = "Azul",
            price = Price(
                priceType = "Type1",
                tolerance = 15,
                maximumPeriod = 800,
                maximumValue = "100",
                valueDetails = listOf(
                    ValueDetail(price = "10", period = 60, since = 15, priceType = "rotativo")
                )
            ),
            date = dateInPast
        )

        val result = repository.calculateValue(vehicle)

        assertEquals(Pair(200, 40.0), result)
    }

}
