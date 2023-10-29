package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.domain.entities.Vehicle
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat


class VehicleToVehicleEntityMapperTest {

    private val sdf = SimpleDateFormat("yyyy-MM-dd")
    private val mapper = VehicleToVehicleEntityMapper()

    @Test
    fun `test map function maps Vehicle to VehicleEntity correctly`() {
        // Criando um mock de Vehicle
        val mockVehicle = mockk<Vehicle>()

        // Definindo o comportamento do mock
        every { mockVehicle.plate } returns "XYZ1234"
        every { mockVehicle.model } returns "ModelTest"
        every { mockVehicle.color } returns "Blue"
        every { mockVehicle.price.priceType } returns "Expensive"
        every { mockVehicle.date } returns sdf.parse("2023-10-29")

        // Chamando a função map
        val result = mapper.map(mockVehicle)

        // Verificando se o resultado é o esperado
        assertEquals("XYZ1234", result.plate)
        assertEquals("ModelTest", result.model)
        assertEquals("Blue", result.color)
        assertEquals("Expensive", result.priceType)
        assertEquals("2023-10-29", sdf.format(result.date))

    }
}
