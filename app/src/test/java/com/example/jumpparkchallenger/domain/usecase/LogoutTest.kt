package com.example.jumpparkchallenger.domain.usecase

import com.example.jumpparkchallenger.domain.repository.MainRepository
import com.example.jumpparkchallenger.domain.usecases.Logout
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LogoutTest {

    @Test
    fun `When Logout useCase is invoked, it should return correct logout status`() = runBlocking {
        // Arrange
        val mockRepository = mockk<MainRepository>()
        coEvery { mockRepository.logout() } returns true
        val logoutUseCase = Logout(mockRepository)

        // Act
        val result = logoutUseCase()

        // Assert
        assertTrue(result)
    }

}