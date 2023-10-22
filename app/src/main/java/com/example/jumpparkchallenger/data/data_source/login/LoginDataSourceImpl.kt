package com.example.jumpparkchallenger.data.data_source.login

import com.example.jumpparkchallenger.data.api.ApiService
import com.example.jumpparkchallenger.data.models.login.LoginDataResponse
import org.json.JSONObject
import java.net.UnknownHostException

class LoginDataSourceImpl(
    private val apiService: ApiService,
) : LoginDataSource {

    override suspend fun login(email: String, password: String): LoginDataResponse {

    val params = mapOf( "email" to email, "password" to password)
        try {
            val response = apiService.login(params)
            when {
                response.isSuccessful -> {
                    return response.body()!!.data!!
                }

                else -> {
                    val jsonString = response.errorBody()?.string() ?: ""
                    val jsonObject = JSONObject(jsonString)
                    val message = jsonObject.getString("data")
                    throw Throwable(message)
                }
            }
        }
        catch (e : UnknownHostException){
            throw Exception("Não foi possível realizar o login, verifique sua conexão e tente novamente.")
        }
    }



}