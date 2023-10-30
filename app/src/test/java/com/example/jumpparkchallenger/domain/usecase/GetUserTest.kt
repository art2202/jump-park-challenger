package com.example.jumpparkchallenger.domain.usecase

import com.example.jumpparkchallenger.domain.repository.MainRepository
import com.example.jumpparkchallenger.domain.usecases.GetUser
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetUserTest {

    @Test
    fun `When GetUser useCase is invoked, it should return correct User`() = runBlocking {
        // Arrange
        val mockRepository = mockk<MainRepository>()
        val expectedUser = "testUser"
        coEvery { mockRepository.getUser() } returns expectedUser
        val getUseruseCase = GetUser(mockRepository)

        // Act
        val result = getUseruseCase()

        // Assert
        assertEquals(expectedUser, result)
    }

}