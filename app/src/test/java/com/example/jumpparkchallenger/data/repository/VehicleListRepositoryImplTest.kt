package com.example.jumpparkchallenger.data.repository

import com.example.jumpparkchallenger.data.data_source.vehicle_list.VehicleListDataSource
import com.example.jumpparkchallenger.data.database.entity.PriceEntity
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import com.example.jumpparkchallenger.data.database.entity.VehicleEntity
import com.example.jumpparkchallenger.data.mapper.VehicleEntityToVehicleMapper
import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.entities.home.Price
import com.example.jumpparkchallenger.domain.entities.home.ValueDetail
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.Date

class VehicleListRepositoryImplTest {

    private val vehicleListDataSource = mockk<VehicleListDataSource>()
    private val vehicleEntityToVehicleMapper = mockk<VehicleEntityToVehicleMapper>()

    private val repository =
        VehicleListRepositoryImpl(vehicleListDataSource, vehicleEntityToVehicleMapper)

    @Before
    fun setUp() {
        clearMocks(vehicleListDataSource, vehicleEntityToVehicleMapper)
    }

    @Test
    fun `getAllVehicles returns correct list of Vehicles when all data is present`() = runBlocking {

        val testVehicleEntity =
            VehicleEntity("testPlate", "testModel", "testColor", "testPriceType", Date())
        val testPriceEntity = PriceEntity("testPriceType", 30, 60, "100")
        val testValueDetailEntity = ValueDetailEntity(1, "50", 30, 10, "testPriceType")

        coEvery { vehicleListDataSource.getAllVehicles() } returns listOf(testVehicleEntity)
        coEvery { vehicleListDataSource.getPrice("testPriceType") } returns testPriceEntity
        coEvery { vehicleListDataSource.getValueDetail("testPriceType") } returns listOf(
            testValueDetailEntity
        )
        every {
            vehicleEntityToVehicleMapper.map(
                Triple(
                    testVehicleEntity,
                    testPriceEntity,
                    listOf(testValueDetailEntity)
                )
            )
        } returns Vehicle(
            "testPlate", "testModel", "testColor", Price(
                "testPriceType", 30, 60, "100", listOf(
                    ValueDetail("50", 30, 10, "test")
                )
            ), Date()
        )


        val result = repository.getAllVehicles()


        assertEquals(1, result.size)
        assertEquals("testPlate", result[0].plate)

        coVerify { vehicleListDataSource.getAllVehicles() }
        coVerify { vehicleListDataSource.getPrice("testPriceType") }
        coVerify { vehicleListDataSource.getValueDetail("testPriceType") }
        verify {
            vehicleEntityToVehicleMapper.map(
                Triple(
                    testVehicleEntity,
                    testPriceEntity,
                    listOf(testValueDetailEntity)
                )
            )
        }
    }

    @Test
    fun `getAllVehicles returns empty list when PriceEntity or ValueDetailEntity is missing`() =
        runBlocking {

            val testVehicleEntity =
                VehicleEntity("testPlate", "testModel", "testColor", "testPriceType", Date())

            coEvery { vehicleListDataSource.getAllVehicles() } returns listOf(testVehicleEntity)
            coEvery { vehicleListDataSource.getPrice("testPriceType") } returns null
            coEvery { vehicleListDataSource.getValueDetail(any()) } returns emptyList()


            val result = repository.getAllVehicles()


            assertTrue(result.isEmpty())

            coVerify { vehicleListDataSource.getAllVehicles() }
            coVerify { vehicleListDataSource.getPrice("testPriceType") }
            coVerify { vehicleListDataSource.getValueDetail(any()) }
        }

}
