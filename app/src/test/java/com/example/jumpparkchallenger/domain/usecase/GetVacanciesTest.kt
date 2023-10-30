package com.example.jumpparkchallenger.domain.usecase

import com.example.jumpparkchallenger.domain.repository.MainRepository
import com.example.jumpparkchallenger.domain.usecases.GetVacancies
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetVacanciesTest {
    @Test
    fun `When GetVacancies useCase is invoked, it should return correct value`() = runBlocking {
        // Arrange
        val mockMainRepository = mockk<MainRepository>()
        val expectedVacancies = 10
        coEvery { mockMainRepository.getVacancies() } returns expectedVacancies
        val getVacanciesUseCase = GetVacancies(mockMainRepository)

        // Act
        val result = getVacanciesUseCase()

        // Assert
        assertEquals(expectedVacancies, result)
    }

}