package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.database.entity.SessionEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class SessionEntityToSessionMapperTest {

    private val mapper = SessionEntityToSessionMapper()

    @Test
    fun `test map function maps SessionEntity to Session correctly`() {
        val testEntity = SessionEntity(
            id = 1,
            establishmentId = 1001
        )

        val result = mapper.map(testEntity)

        assertEquals(1, result.id)
        assertEquals(1001, result.establishmentId)
    }
}
