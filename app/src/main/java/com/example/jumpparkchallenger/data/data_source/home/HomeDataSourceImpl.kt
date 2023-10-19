package com.example.jumpparkchallenger.data.data_source.home

import com.example.jumpparkchallenger.core.utils.SharedPreferenceHelper
import com.example.jumpparkchallenger.data.api.ApiService
import com.example.jumpparkchallenger.data.models.home.HomeData

class HomeDataSourceImpl(private val apiService: ApiService, private val sharedPreferenceHelper: SharedPreferenceHelper) : HomeDataSource {
    override suspend fun getData(userId: Int, establishmentId: Int): HomeData {

        val response = apiService.manualLoad(userId, establishmentId, "Bearer " + sharedPreferenceHelper.getToken())
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