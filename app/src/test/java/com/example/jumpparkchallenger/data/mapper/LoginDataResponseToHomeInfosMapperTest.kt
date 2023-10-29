package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.models.login.EstablishmentDataResponse
import com.example.jumpparkchallenger.data.models.login.LoginDataResponse
import com.example.jumpparkchallenger.data.models.login.SessionDataResponse
import com.example.jumpparkchallenger.data.models.login.UserDataResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class LoginDataResponseToHomeInfosMapperTest {

    private val mapper = LoginDataResponseToHomeInfosMapper()

    @Test
    fun `test map function maps LoginDataResponse to LoginInfos correctly`() {

        val testLoginDataResponse = LoginDataResponse(
            userDataResponse = UserDataResponse(
                id = 1,
                name = "Arthur Rodrigues",
                email = "arthur.rodrigues@example.com",
                accessToken = "token"
            ),
            sessionDataResponse = SessionDataResponse(
                id = 2,
                establishmentId = 1001
            ),
            establishmentDataResponse = listOf(
                EstablishmentDataResponse(
                id = 3,
                name = "Establishment A",
                vacanciesMarks = 5
            )
            )
        )

        val result = mapper.map(testLoginDataResponse)

        with(result) {
            assertEquals(1, user.id)
            assertEquals("Arthur Rodrigues", user.name)
            assertEquals("arthur.rodrigues@example.com", user.email)
            assertEquals("token", user.accessToken)

            assertEquals(2, session.id)
            assertEquals(1001, session.establishmentId)

            assertEquals(3, establishment.id)
            assertEquals("Establishment A", establishment.name)
            assertEquals(5, establishment.vacanciesMarks)
        }
    }
}
