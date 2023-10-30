package com.example.jumpparkchallenger.domain.usecase

import com.example.jumpparkchallenger.domain.entities.home.Price
import com.example.jumpparkchallenger.domain.repository.CheckInRepository
import com.example.jumpparkchallenger.domain.usecases.GetPrices
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetPricesTest {

    @Test
    fun `When GetPrices useCase is invoked, it should return correct list of Prices`() = runBlocking {
        // Arrange
        val mockRepository = mockk<CheckInRepository>()
        val expectedPrices = listOf(mockk<Price>(), mockk<Price>())
        coEvery { mockRepository.getPrices() } returns expectedPrices
        val getPricesUseCase = GetPrices(mockRepository)

        // Act
        val result = getPricesUseCase()

        // Assert
        assertEquals(expectedPrices, result)
    }

}