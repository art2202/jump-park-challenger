package com.example.jumpparkchallenger.domain.usecase

import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import com.example.jumpparkchallenger.domain.repository.CheckOutRepository
import com.example.jumpparkchallenger.domain.usecases.GetPaymentsMethod
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetPaymentsMethodTest {

    @Test
    fun `When GetPaymentsMethod useCase is invoked, it should return correct list of PaymentMethods`() = runBlocking {
        // Arrange
        val mockRepository = mockk<CheckOutRepository>()
        val expectedMethods = listOf(mockk<PaymentMethod>(), mockk<PaymentMethod>())
        coEvery { mockRepository.getPaymentsMethod() } returns expectedMethods
        val getPaymentsMethodUseCase = GetPaymentsMethod(mockRepository)

        // Act
        val result = getPaymentsMethodUseCase()

        // Assert
        assertEquals(expectedMethods, result)
    }

}