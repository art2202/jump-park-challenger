package com.example.jumpparkchallenger.domain.usecase

import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import com.example.jumpparkchallenger.domain.repository.CheckOutRepository
import com.example.jumpparkchallenger.domain.usecases.CheckOut
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CheckOutTest {

    @Test
    fun `When CheckOut useCase is invoked, it should correctly forward parameters to repository`() = runBlocking {
        // Arrange
        val mockRepository = mockk<CheckOutRepository>(relaxed = true)
        val vehicle = mockk<Vehicle>()
        val paymentMethod = mockk<PaymentMethod>()
        coEvery { mockRepository.checkOut(vehicle, paymentMethod) } just Runs
        val checkOutUseCase = CheckOut(mockRepository)

        // Act
        checkOutUseCase(vehicle, paymentMethod)

        // Assert
        coVerify { mockRepository.checkOut(vehicle, paymentMethod) }
    }

}