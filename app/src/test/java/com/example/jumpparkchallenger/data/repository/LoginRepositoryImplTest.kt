package com.example.jumpparkchallenger.data.repository

import com.example.jumpparkchallenger.data.data_source.login.LoginDataSource
import com.example.jumpparkchallenger.data.data_source.login.LoginLocalDataSource
import com.example.jumpparkchallenger.data.database.entity.UserEntity
import com.example.jumpparkchallenger.data.mapper.EstablishmentDataResponseToEstablishmentEntityMapper
import com.example.jumpparkchallenger.data.mapper.LoginDataResponseToHomeInfosMapper
import com.example.jumpparkchallenger.data.mapper.SessionDataResponseToSessionEntityMapper
import com.example.jumpparkchallenger.data.mapper.UserDataResponseToUserEntityMapper
import com.example.jumpparkchallenger.data.models.login.LoginDataResponse
import com.example.jumpparkchallenger.data.models.login.UserDataResponse
import com.example.jumpparkchallenger.domain.entities.LoginInfos
import io.mockk.*
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LoginRepositoryImplTest {

    private val loginDataSource: LoginDataSource = mockk()
    private val loginLocalDataSource: LoginLocalDataSource = mockk()
    private val loginMapper: LoginDataResponseToHomeInfosMapper = mockk()
    private val userDataResponseToUserEntityMapper: UserDataResponseToUserEntityMapper = mockk()
    private val sessionDataResponseToSessionEntityMapper: SessionDataResponseToSessionEntityMapper =
        mockk()
    private val establishmentDataResponseToEstablishmentEntityMapper: EstablishmentDataResponseToEstablishmentEntityMapper =
        mockk()

    private lateinit var repository: LoginRepositoryImpl

    @Before
    fun setUp() {
        repository = LoginRepositoryImpl(
            loginDataSource,
            loginLocalDataSource,
            loginMapper,
            userDataResponseToUserEntityMapper,
            sessionDataResponseToSessionEntityMapper,
            establishmentDataResponseToEstablishmentEntityMapper
        )
        clearAllMocks()
    }

    @Test
    fun `test checkToken returns true when token is not empty`() = runBlocking {
        val token = "token123"

        coEvery { loginLocalDataSource.getLocalToken() } returns token

        val result = repository.checkToken()

        assertTrue(result)
    }

    @Test
    fun `test checkToken returns false when token is empty`() = runBlocking {
        val token = ""

        coEvery { loginLocalDataSource.getLocalToken() } returns token

        val result = repository.checkToken()

        assertFalse(result)
    }
}
