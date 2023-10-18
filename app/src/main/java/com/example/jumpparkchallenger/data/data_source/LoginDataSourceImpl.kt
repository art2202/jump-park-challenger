package com.example.jumpparkchallenger.data.data_source

import com.example.jumpparkchallenger.core.utils.SharedPreferenceHelper
import com.example.jumpparkchallenger.data.api.ApiService
import com.example.jumpparkchallenger.data.models.LoginDataResponse

class LoginDataSourceImpl(
    private val apiService: ApiService,
    private val sharedPreferenceHelper: SharedPreferenceHelper
) : LoginDataSource {

    override suspend fun login(email: String, password: String): LoginDataResponse {

    val params = mapOf( "email" to email, "password" to password)
        val response = apiService.login(params)
        when {
            response.isSuccessful -> {
                saveData(response.body()!!.data!!)
                return response.body()!!.data!!
            }
            response.code() == 404 -> {
                throw Throwable("Usuário não encontrado")
            }
            else -> throw Throwable()
        }
    }

    private fun saveData(body: LoginDataResponse) {
        // TODO: implementar room para salvar informações de login
        saveToken(body.userDataResponse?.accessToken)
    }
    private fun saveToken(token : String?) {
        if(token != null)
            sharedPreferenceHelper.saveToken(token)
        else
            throw Throwable("Não foi possivel pegar o token")
    }

    override fun getLocalToken() = sharedPreferenceHelper.getToken()

}