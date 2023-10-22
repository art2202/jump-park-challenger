package com.example.jumpparkchallenger.data.data_source.main

import com.example.jumpparkchallenger.core.utils.SharedPreferenceHelper
import com.example.jumpparkchallenger.data.api.ApiService
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainDataSourceImpl(
    private val apiService: ApiService,
    private val sharedPreferenceHelper: SharedPreferenceHelper
) : MainDataSource {

    override suspend fun logout(userId: Int, establishmentId: Int, sessionId: Int) : Boolean {

        val body = mapOf<String, String>()

        try {
            val response = apiService.logout(
                userId,
                establishmentId,
                sessionId,
                "Bearer " + sharedPreferenceHelper.getToken()
            )

            return when {
                response.isSuccessful -> {
                    true
                }

                response.code() == 400 -> {
                    throw Throwable("Bad Request")
                }

                else -> false
            }
        }
        catch (e : UnknownHostException){
            throw Exception("Não foi possível realizar o logout, verifique sua conexão e tente novamente.")
        }
    }
}