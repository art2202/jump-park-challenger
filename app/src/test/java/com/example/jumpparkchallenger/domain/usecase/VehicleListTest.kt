package com.example.jumpparkchallenger.domain.usecase

import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.repository.VehicleListRepository
import com.example.jumpparkchallenger.domain.usecases.VehicleList
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class VehicleListTest {

    @Test
    fun `When VehicleList useCase is invoked, it should return correct vehicle list`() = runBlocking {
        // Arrange
        val mockRepository = mockk<VehicleListRepository>()
        val expectedVehicles = listOf(mockk<Vehicle>(), mockk<Vehicle>())
        coEvery { mockRepository.getAllVehicles() } returns expectedVehicles
        val vehicleListuseCase = VehicleList(mockRepository)

        // Act
        val result = vehicleListuseCase()

        // Assert
        assertEquals(expectedVehicles, result)
    }

}