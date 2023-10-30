package com.example.jumpparkchallenger.domain.usecase

import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.repository.CheckInRepository
import com.example.jumpparkchallenger.domain.usecases.SaveVehicle
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SaveVehicleTest {
    @Test
    fun `When SaveVehicle useCase is invoked with a vehicle, it should return correct saving status`() = runBlocking {
        // Arrange
        val mockRepository = mockk<CheckInRepository>()
        val testVehicle = mockk<Vehicle>()
        coEvery { mockRepository.saveVehicle(testVehicle) } returns true
        val saveVehicleUseCase = SaveVehicle(mockRepository)

        // Act
        val result = saveVehicleUseCase(testVehicle)

        // Assert
        assertTrue(result)
    }

}