package com.example.jumpparkchallenger.data.mapper
import com.example.jumpparkchallenger.data.models.login.EstablishmentDataResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class EstablishmentDataResponseToEstablishmentEntityMapperTest {

    private val mapper = EstablishmentDataResponseToEstablishmentEntityMapper()

    @Test
    fun `test map function maps EstablishmentDataResponse to EstablishmentEntity correctly`() {

        val testEstablishmentDataResponse = EstablishmentDataResponse(
            id = 1,
            name = "Establishment A",
            vacanciesMarks = 20
        )

        val result = mapper.map(testEstablishmentDataResponse)

        assertEquals(1, result.id)
        assertEquals("Establishment A", result.name)
        assertEquals(20, result.vacanciesMarks)
    }
}
