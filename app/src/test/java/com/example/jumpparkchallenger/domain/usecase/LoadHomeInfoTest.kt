package com.example.jumpparkchallenger.domain.usecase

import com.example.jumpparkchallenger.domain.entities.home.HomeInfos
import com.example.jumpparkchallenger.domain.repository.HomeRepository
import com.example.jumpparkchallenger.domain.usecases.LoadHomeInfo
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LoadHomeInfoTest {

    @Test
    fun `When LoadHomeInfo useCase is invoked, it should return correct HomeInfos`() = runBlocking {
        // Arrange
        val mockRepository = mockk<HomeRepository>()
        val expectedHomeInfos = mockk<HomeInfos>()
        coEvery { mockRepository.getData() } returns expectedHomeInfos
        val loadHomeInfoUseCase = LoadHomeInfo(mockRepository)

        // Act
        val result = loadHomeInfoUseCase()

        // Assert
        assertEquals(expectedHomeInfos, result)
    }

}