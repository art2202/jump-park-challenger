package com.example.jumpparkchallenger.data.mapper
import com.example.jumpparkchallenger.data.models.login.UserDataResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class UserDataResponseToUserEntityMapperTest {

    private val mapper = UserDataResponseToUserEntityMapper()

    @Test
    fun `test map function maps UserDataResponse to UserEntity correctly`() {
        val testDataResponse = UserDataResponse(
            id = 1,
            name = "Arthur Rodrigues",
            email = "arthur.rodrigues@example.com",
            accessToken = null
        )

        val result = mapper.map(testDataResponse)


        assertEquals(1, result.id)
        assertEquals("Arthur Rodrigues", result.name)
        assertEquals("arthur.rodrigues@example.com", result.email)
    }

}