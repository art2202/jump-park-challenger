package com.example.jumpparkchallenger.data.data_source.login

import com.example.jumpparkchallenger.data.api.ApiService
import com.example.jumpparkchallenger.data.models.login.LoginDataResponse

class LoginDataSourceImpl(
    private val apiService: ApiService,
) : LoginDataSource {

    override suspend fun login(email: String, password: String): LoginDataResponse {

    val params = mapOf( "email" to email, "password" to password)
        val response = apiService.login(params)
        when {
            response.isSuccessful -> {
                return response.body()!!.data!!
            }
            response.code() == 404 -> {
                throw Throwable("Usuário não encontrado")
            }
            else -> throw Throwable()
        }
    }



}