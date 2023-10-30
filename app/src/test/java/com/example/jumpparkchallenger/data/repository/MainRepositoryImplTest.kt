package com.example.jumpparkchallenger.data.repository
import com.example.jumpparkchallenger.data.data_source.main.MainDataSource
import com.example.jumpparkchallenger.data.data_source.main.MainLocalDataSource
import com.example.jumpparkchallenger.data.data_source.vehicle_list.VehicleListDataSource
import com.example.jumpparkchallenger.data.database.entity.EstablishmentEntity
import com.example.jumpparkchallenger.data.database.entity.UserEntity
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MainRepositoryImplTest {

    private val mainDataSource: MainDataSource = mockk()
    private val mainLocalDataSource: MainLocalDataSource = mockk()
    private val vehicleListDataSource: VehicleListDataSource = mockk()

    private lateinit var repository: MainRepositoryImpl

    @Before
    fun setUp() {
        repository = MainRepositoryImpl(mainDataSource, mainLocalDataSource, vehicleListDataSource)
        clearAllMocks()
    }

    @Test
    fun `test getUser returns correct email`() = runBlocking {
        val email = "test@example.com"

        coEvery { mainLocalDataSource.getUser() } returns UserEntity(1, "test","test@example.com")

        val result = repository.getUser()

        assertEquals(email, result)
    }

    @Test
    fun `test getVacancies returns correct vacancies count`() = runBlocking {
        val totalVacancies = 10
        val totalVehicles = 5

        coEvery { mainLocalDataSource.getEstablishment() } returns EstablishmentEntity(1," test", totalVacancies)
        coEvery { vehicleListDataSource.getAllVehicles() } returns List(totalVehicles) { mockk() }

        val result = repository.getVacancies()

        assertEquals(totalVacancies - totalVehicles, result)
    }


}
