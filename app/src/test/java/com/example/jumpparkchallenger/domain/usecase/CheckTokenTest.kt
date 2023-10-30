package com.example.jumpparkchallenger.domain.usecase

import com.example.jumpparkchallenger.domain.repository.LoginRepository
import com.example.jumpparkchallenger.domain.usecases.CheckToken
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CheckTokenTest {

    @Test
    fun `When CheckToken useCase is invoked, it should return correct Boolean`() = runBlocking {
        // Arrange
        val mockRepository = mockk<LoginRepository>()
        coEvery { mockRepository.checkToken() } returns true
        val checkTokenUseCase = CheckToken(mockRepository)

        // Act
        val result = checkTokenUseCase()

        // Assert
        assertTrue(result)
    }

}