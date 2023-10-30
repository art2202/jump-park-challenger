package com.example.jumpparkchallenger.domain.usecase

import com.example.jumpparkchallenger.domain.entities.LoginInfos
import com.example.jumpparkchallenger.domain.repository.LoginRepository
import com.example.jumpparkchallenger.domain.usecases.Login
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LoginTest {

    @Test
    fun `When Login useCase is invoked, it should return correct LoginInfos`() = runBlocking {
        // Arrange
        val mockRepository = mockk<LoginRepository>()
        val expectedLoginInfos = mockk<LoginInfos>()
        val testEmail = "test@example.com"
        val testPassword = "password"
        coEvery { mockRepository.login(testEmail, testPassword) } returns expectedLoginInfos
        val loginUseCase = Login(mockRepository)

        // Act
        val result = loginUseCase(testEmail, testPassword)

        // Assert
        assertEquals(expectedLoginInfos, result)
    }

}